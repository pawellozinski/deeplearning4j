/*
 *  ******************************************************************************
 *  *
 *  *
 *  * This program and the accompanying materials are made available under the
 *  * terms of the Apache License, Version 2.0 which is available at
 *  * https://www.apache.org/licenses/LICENSE-2.0.
 *  *
 *  *  See the NOTICE file distributed with this work for additional
 *  *  information regarding copyright ownership.
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  * License for the specific language governing permissions and limitations
 *  * under the License.
 *  *
 *  * SPDX-License-Identifier: Apache-2.0
 *  *****************************************************************************
 */

package org.eclipse.deeplearning4j.nd4j.linalg.lossfunctions;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.nd4j.common.tests.tags.NativeTag;
import org.nd4j.common.tests.tags.TagNames;
import org.nd4j.linalg.BaseNd4jTestWithBackends;
import org.nd4j.linalg.activations.IActivation;
import org.nd4j.linalg.activations.impl.ActivationSigmoid;
import org.nd4j.linalg.activations.impl.ActivationSoftmax;
import org.nd4j.linalg.api.buffer.DataType;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.impl.reduce.longer.MatchCondition;
import org.nd4j.linalg.api.ops.random.impl.BernoulliDistribution;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.factory.Nd4jBackend;
import org.nd4j.linalg.indexing.conditions.Conditions;
import org.nd4j.linalg.lossfunctions.ILossFunction;
import org.nd4j.linalg.lossfunctions.impl.LossBinaryXENT;
import org.nd4j.linalg.lossfunctions.impl.LossL1;
import org.nd4j.linalg.lossfunctions.impl.LossL2;
import org.nd4j.linalg.lossfunctions.impl.LossMAE;
import org.nd4j.linalg.lossfunctions.impl.LossMAPE;
import org.nd4j.linalg.lossfunctions.impl.LossMCXENT;
import org.nd4j.linalg.lossfunctions.impl.LossMSE;
import org.nd4j.linalg.lossfunctions.impl.LossMSLE;
import org.nd4j.linalg.lossfunctions.impl.LossNegativeLogLikelihood;
import org.nd4j.linalg.lossfunctions.impl.LossSparseMCXENT;

import static org.junit.jupiter.api.Assertions.*;
@Tag(TagNames.LOSS_FUNCTIONS)
@Tag(TagNames.TRAINING)
@NativeTag
@Tag(TagNames.DL4J_OLD_API)
public class LossFunctionTest extends BaseNd4jTestWithBackends {



    @ParameterizedTest
    @MethodSource("org.nd4j.linalg.BaseNd4jTestWithBackends#configs")
    public void testWeightedLossFunctionDTypes(Nd4jBackend backend) {

        for(DataType activationsDt : new DataType[]{DataType.DOUBLE, DataType.FLOAT, DataType.HALF}) {
            for(DataType weightsDt : new DataType[]{DataType.DOUBLE, DataType.FLOAT, DataType.HALF}) {
                for( boolean rank1W : new boolean[]{false, true}) {

                    INDArray preOut = Nd4j.rand(activationsDt, 2, 3);
                    INDArray l = Nd4j.rand(activationsDt, 2, 3);

                    INDArray w = Nd4j.createFromArray(1.0f, 2.0f, 3.0f).castTo(weightsDt);
                    if(!rank1W) {
                        w = w.reshape(1, 3);
                    }

                    ILossFunction lf = null;
                    for (int i = 0; i < 10; i++) {
                        switch (i) {
                            case 0:
                                lf = new LossBinaryXENT(w);
                                break;
                            case 1:
                                lf = new LossL1(w);
                                break;
                            case 2:
                                lf = new LossL2(w);
                                break;
                            case 3:
                                lf = new LossMAE(w);
                                break;
                            case 4:
                                lf = new LossMAPE(w);
                                break;
                            case 5:
                                lf = new LossMCXENT(w);
                                break;
                            case 6:
                                lf = new LossMSE(w);
                                break;
                            case 7:
                                lf = new LossMSLE(w);
                                break;
                            case 8:
                                lf = new LossNegativeLogLikelihood(w);
                                break;
                            case 9:
                                lf = new LossSparseMCXENT(w);
                                l = Nd4j.createFromArray(1,2).reshape(2, 1).castTo(activationsDt);
                                break;
                            default:
                                throw new RuntimeException();
                        }
                    }

                    //Check score
                    lf.computeScore(l, preOut, new ActivationSoftmax(), null, true);

                    //Check backward
                    lf.computeGradient(l, preOut, new ActivationSoftmax(), null);
                }
            }
        }

    }


    @Override
    public char ordering() {
        return 'c';
    }
}

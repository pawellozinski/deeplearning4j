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

package org.nd4j.linalg.jcublas.buffer;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.indexer.Indexer;
import org.nd4j.jita.allocator.impl.AllocationShape;
import org.nd4j.jita.allocator.impl.AtomicAllocator;
import org.nd4j.linalg.api.buffer.DataBuffer;
import org.nd4j.linalg.api.buffer.DataType;
import org.nd4j.linalg.api.memory.MemoryWorkspace;
import org.nd4j.common.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Cuda float buffer
 *
 * @author Adam Gibson
 */
@Slf4j
public class CudaFloatDataBuffer extends BaseCudaDataBuffer {
    /**
     * Meant for creating another view of a buffer
     *
     * @param pointer the underlying buffer to create a view from
     * @param indexer the indexer for the pointer
     * @param length  the length of the view
     */
    public CudaFloatDataBuffer(Pointer pointer, Indexer indexer, long length) {
        super(pointer, indexer, length);
    }

    public CudaFloatDataBuffer(Pointer pointer, Pointer specialPointer, Indexer indexer, long length){
        super(pointer, specialPointer, indexer, length);
    }

    public CudaFloatDataBuffer(ByteBuffer buffer, DataType dataType, long length, long offset) {
        super(buffer, dataType, length, offset);
    }

    /**
     * Base constructor
     *
     * @param length the length of the buffer
     */
    public CudaFloatDataBuffer(long length) {
        super(length, 4, true);
    }

    public CudaFloatDataBuffer(long length, boolean initialize) {
        super(length, 4, initialize);
    }

    public CudaFloatDataBuffer(long length, boolean initialize, MemoryWorkspace workspace) {
        super(length, 4, initialize, workspace);
    }


    public CudaFloatDataBuffer(long length, int elementSize) {
        super(length, elementSize);
    }

    public CudaFloatDataBuffer(float[] data, boolean copy, MemoryWorkspace workspace) {
        super(data.length, 4, true, workspace);
        setData(data);
    }

    public CudaFloatDataBuffer(ByteBuffer underlyingBuffer, DataType dataType, long length) {
        super(underlyingBuffer, dataType, length);
    }


    /**
     * Initialize the opType of this buffer
     */
    @Override
    protected void initTypeAndSize() {
        elementSize = 4;
        type = DataType.FLOAT;
    }



    public CudaFloatDataBuffer(float[] buffer) {
        super(buffer.length, 4, true);
        setData(buffer);
    }

    public CudaFloatDataBuffer(double[] data) {
        super(data.length, 4, true);
        setData(data);
    }

    public CudaFloatDataBuffer(int[] data) {
        super(data.length, 4, true);
        setData(data);
    }

    @Override
    protected DataBuffer create(long length) {
        return new CudaFloatDataBuffer(length);
    }

    @Override
    public double[] getDoublesAt(long offset, long inc, int length) {
        return ArrayUtil.toDoubles(getFloatsAt(offset, inc, length));
    }

    @Override
    public void setData(int[] data) {
        if (data.length == 0)
            return;
        float[] floatData = ArrayUtil.toFloats(data);
        set(floatData, floatData.length, 0, 0);
    }

    @Override
    public void setData(double[] data) {
        if (data.length == 0)
            return;
        float[] floatData = ArrayUtil.toFloats(data);
        set(floatData, floatData.length, 0, 0);
    }

    @Override
    public byte[] asBytes() {
        float[] data = asFloat();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        for (int i = 0; i < data.length; i++)
            try {
                dos.writeFloat(data[i]);
            } catch (IOException e) {
                log.error("",e);
            }
        return bos.toByteArray();
    }

    @Override
    public DataType dataType() {
        return type;
    }



    @Override
    public double[] asDouble() {
        return ArrayUtil.toDoubles(asFloat());
    }

    @Override
    public int[] asInt() {
        return ArrayUtil.toInts(asFloat());
    }


    @Override
    public double getDouble(long i) {
        return super.getFloat(i);
    }


    @Override
    public DataBuffer create(double[] data) {
        return new CudaFloatDataBuffer(data);
    }

    @Override
    public DataBuffer create(float[] data) {
        return new CudaFloatDataBuffer(data);
    }

    @Override
    public DataBuffer create(int[] data) {
        return new CudaFloatDataBuffer(data);
    }

    @Override
    public void flush() {

    }
}

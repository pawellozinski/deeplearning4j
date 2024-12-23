/* ******************************************************************************
 *
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 *  See the NOTICE file distributed with this work for additional
 *  information regarding copyright ownership.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 ******************************************************************************/

//
//  @author raver119@gmail.com
//

#ifndef LIBND4J_HEADERS_SHAPE_H
#define LIBND4J_HEADERS_SHAPE_H
#include <ops/declarable/headers/common.h>

namespace sd {
namespace ops {

#define RESHAPE_NO_COPY_F_ORDER_MARKER -102
#define RESHAPE_NO_COPY_C_ORDER_MARKER -99

#if NOT_EXCLUDED(OP_permute)
DECLARE_CUSTOM_OP(permute, 1, 1, false, 0, -2);
#endif

#if NOT_EXCLUDED(OP_reshapeas)
DECLARE_CUSTOM_OP(reshapeas, 2, 1, false, 0, 0);
#endif

#if NOT_EXCLUDED(OP_linear_copy)
DECLARE_CUSTOM_OP(linear_copy, 2, 1, false, 0, 0);
#endif


#if NOT_EXCLUDED(OP_transpose)
DECLARE_CUSTOM_OP(transpose, 1, 1, false, 0, 0);
#endif




#if NOT_EXCLUDED(OP_shape_of)
DECLARE_CUSTOM_OP(shape_of, 1, 1, false, 0, 0);
#endif

#if NOT_EXCLUDED(OP_shapes_of)
DECLARE_CUSTOM_OP(shapes_of, -1, -1, false, 0, 0);
#endif

#if NOT_EXCLUDED(OP_set_shape)
DECLARE_CUSTOM_OP(set_shape, 2, 1, true, 0, 0);
#endif

#if NOT_EXCLUDED(OP_squeeze)
DECLARE_CUSTOM_OP(squeeze, 1, 1, false, 0, -2);
#endif

#if NOT_EXCLUDED(OP_expand_dims)
DECLARE_CUSTOM_OP(expand_dims, 1, 1, false, 0, -2);
#endif

#if NOT_EXCLUDED(OP_flatten_2d)
DECLARE_CUSTOM_OP(flatten_2d, 1, 1, false, 0, 1);
#endif

#if NOT_EXCLUDED(OP_reshape)
DECLARE_CUSTOM_OP(reshape, 1, 1, false, 0, -2);
#endif

#if NOT_EXCLUDED(OP_reshape_no_copy)
DECLARE_CUSTOM_OP(reshape_no_copy, -2, 1, false, 0, -2);
#endif

#if NOT_EXCLUDED(OP_size_at)
DECLARE_CUSTOM_OP(size_at, 1, 1, false, 0, 1);
#endif

/**
 * This op changes order of given array to specified order.
 * In other words: C/F order switch
 *
 * Int args:
 * 0 - isForder. set to 1 for F order output, or 0 for C order output
 *
 * @tparam T
 */
#if NOT_EXCLUDED(OP_order)
DECLARE_CUSTOM_OP(order, 1, 1, false, 0, 1);
#endif

/**
 * This op boosts specified input up to specified shape
 *
 * @tparam T
 */
#if NOT_EXCLUDED(OP_tile_to_shape)
DECLARE_CUSTOM_OP(tile_to_shape, 1, 1, false, 0, -1);
DECLARE_CUSTOM_OP(tile_to_shape_bp, 2, 1, false, 0, -1);
#endif

/**
 * This op broadcast given input up to given shape
 *
 * inputs:
 *  input array - array to be broadcasted to given shape
 *  shape array - array containing shape be broadcasted to
 */
#if NOT_EXCLUDED(OP_broadcast_to)
DECLARE_CUSTOM_OP(broadcast_to, 2, 1, false, 0, 0);
#endif

#if NOT_EXCLUDED(OP_evaluate_reduction_shape)
DECLARE_CUSTOM_OP(evaluate_reduction_shape, 2, 1, false, 0, 0);
#endif

/**
 * This operation creates new array
 * Input:
 *    array with shape values
 *
 * IArgs:
 *    order value
 *    data type value
 *
 * BArgs:
 *    initialization option
 */
#if NOT_EXCLUDED(OP_create)
DECLARE_CUSTOM_OP(create, 1, 1, false, 0, 1);
#endif
}  // namespace ops
}  // namespace sd

#endif

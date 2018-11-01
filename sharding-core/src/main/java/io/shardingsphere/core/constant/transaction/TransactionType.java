/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.core.constant.transaction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Transaction type.
 *
 * @author zhaojun
 */
@RequiredArgsConstructor
@Getter
public enum TransactionType {
    
    LOCAL("none"),
    
    XA("io.shardingsphere.transaction.manager.xa.XATransactionManager"),
    
    BASE("io.shardingsphere.transaction.manager.base.BASETransactionManager");
    
    private final String spiClassName;
    
    /**
     * Find transaction type by class name.
     *
     * @param className class name
     * @return transaction type
     */
    public static TransactionType find(final String className) {
        for (TransactionType each : TransactionType.values()) {
            if (className.equals(each.spiClassName)) {
                return each;
            }
        }
        throw new UnsupportedOperationException(String.format("Cannot find transaction type of [%s]", className));
    }
}

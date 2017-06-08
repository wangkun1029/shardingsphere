/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.dangdang.ddframe.rdb.sharding.executor.wrapper;

import com.dangdang.ddframe.rdb.sharding.constant.SQLType;
import com.dangdang.ddframe.rdb.sharding.executor.event.DMLExecutionEvent;
import com.dangdang.ddframe.rdb.sharding.executor.event.DQLExecutionEvent;
import com.dangdang.ddframe.rdb.sharding.executor.event.ExecutionEvent;
import com.dangdang.ddframe.rdb.sharding.routing.SQLExecutionUnit;
import com.google.common.base.Optional;
import lombok.Getter;

import java.sql.Statement;

/**
 * 静态语句对象的执行上下文.
 * 
 * @author zhangliang
 */
public final class StatementExecutorWrapper extends AbstractExecutorWrapper {
    
    @Getter
    private final Statement statement;
    
    private final Optional<? extends ExecutionEvent> executionEvent;
    
    public StatementExecutorWrapper(final SQLType sqlType, final Statement statement, final SQLExecutionUnit sqlExecutionUnit) {
        super(sqlExecutionUnit);
        this.statement = statement;
        switch (sqlType) {
            case SELECT:
                executionEvent = Optional.of(new DQLExecutionEvent(getSqlExecutionUnit().getDataSource(), getSqlExecutionUnit().getSql()));
                break;
            case INSERT:
            case UPDATE:
            case DELETE:
                executionEvent = Optional.of(new DMLExecutionEvent(getSqlExecutionUnit().getDataSource(), getSqlExecutionUnit().getSql()));
                break;
            default:
                executionEvent = Optional.absent();
        }
    }
    
    @Override
    public Optional<? extends ExecutionEvent> getExecutionEvent() {
        return executionEvent;
    }
}

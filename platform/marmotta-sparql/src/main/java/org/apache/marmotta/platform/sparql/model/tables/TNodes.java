/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * This class is generated by jOOQ
 */
package org.apache.marmotta.platform.sparql.model.tables;

import org.apache.marmotta.platform.sparql.model.tables.records.RNodes;
import org.jooq.impl.SQLDataType;

import java.sql.Timestamp;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "2.2.0"},
                            comments = "This class is generated by jOOQ")
public class TNodes extends org.jooq.impl.TableImpl<RNodes> {

	private static final long serialVersionUID = 377156424;

	/**
	 * The singleton instance of public.kiwinode
	 */
	public static final TNodes KIWINODE = new TNodes();

	/**
	 * The class holding records for this type
	 */
	private static final java.lang.Class<RNodes> __RECORD_TYPE = RNodes.class;

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<RNodes> getRecordType() {
		return __RECORD_TYPE;
	}

	/**
	 * An uncommented item
	 */
	public final org.jooq.TableField<RNodes, java.lang.String> NODETYPE = createField("nodetype", org.jooq.impl.SQLDataType.VARCHAR, this);

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public final org.jooq.TableField<RNodes, java.lang.Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT, this);

	/**
	 * An uncommented item
	 */
	public final org.jooq.TableField<RNodes, java.sql.Timestamp> CREATED = createField("created", org.jooq.impl.SQLDataType.TIMESTAMP, this);

	/**
	 * An uncommented item
	 */
	public final org.jooq.TableField<RNodes, java.lang.Boolean> DELETED = createField("deleted", org.jooq.impl.SQLDataType.BOOLEAN, this);

	/**
	 * An uncommented item
	 */
	public final org.jooq.TableField<RNodes, java.lang.Long> VERSION = createField("version", org.jooq.impl.SQLDataType.BIGINT, this);

	/**
	 * An uncommented item
	 */
	public final org.jooq.TableField<RNodes, java.lang.String> URI = createField("uri", org.jooq.impl.SQLDataType.VARCHAR, this);

	/**
	 * An uncommented item
	 */
	public final org.jooq.TableField<RNodes, java.lang.String> ANONID = createField("anonid", org.jooq.impl.SQLDataType.VARCHAR, this);

	/**
	 * An uncommented item
	 */
	public final org.jooq.TableField<RNodes, java.lang.String> CONTENTMD5 = createField("contentmd5", org.jooq.impl.SQLDataType.VARCHAR, this);

	/**
	 * An uncommented item
	 */
	public final org.jooq.TableField<RNodes, java.lang.String> LANGUAGE = createField("language", org.jooq.impl.SQLDataType.VARCHAR, this);

	/**
	 * An uncommented item
	 */
	public final org.jooq.TableField<RNodes, java.lang.String> TYPE = createField("type", org.jooq.impl.SQLDataType.VARCHAR, this);

	/**
	 * An uncommented item
	 */
	public final org.jooq.TableField<RNodes, java.lang.String> CONTENT = createField("content", org.jooq.impl.SQLDataType.CLOB, this);

	/**
	 * An uncommented item
	 */
	public final org.jooq.TableField<RNodes, java.lang.Long> INTCONTENT = createField("intcontent", org.jooq.impl.SQLDataType.BIGINT, this);

	/**
	 * An uncommented item
	 */
	public final org.jooq.TableField<RNodes, java.lang.Double> DOUBLECONTENT = createField("doublecontent", org.jooq.impl.SQLDataType.DOUBLE, this);


    public final org.jooq.TableField<RNodes, Timestamp> DATECONTENT = createField("datecontent", SQLDataType.TIMESTAMP, this);

    /**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * CONSTRAINT kiwinode__fkbbc8d71252fbdfe5
	 * FOREIGN KEY (context_id)
	 * REFERENCES public.kiwinode (id)
	 * </pre></code>
	 */
	public final org.jooq.TableField<RNodes, java.lang.Long> CONTEXT_ID = createField("context_id", org.jooq.impl.SQLDataType.BIGINT, this);

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * CONSTRAINT kiwinode__fkbbc8d71236cf857c
	 * FOREIGN KEY (creator_id)
	 * REFERENCES public.kiwinode (id)
	 * </pre></code>
	 */
	public final org.jooq.TableField<RNodes, java.lang.Long> CREATOR_ID = createField("creator_id", org.jooq.impl.SQLDataType.BIGINT, this);

	public TNodes() {
		super("kiwinode", org.apache.marmotta.platform.sparql.model.Public.PUBLIC);
	}

	public TNodes(java.lang.String alias) {
		super(alias, org.apache.marmotta.platform.sparql.model.Public.PUBLIC, TNodes.KIWINODE);
	}

	@Override
	public TNodes as(java.lang.String alias) {
		return new TNodes(alias);
	}
}

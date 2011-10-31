package net.smartworks.server.engine.common.model.util;

import java.io.Serializable;

import net.smartworks.server.engine.common.model.ClassObject;

import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.UUIDHexGenerator;

public class IdGen extends UUIDHexGenerator {
	public IdGen() {
		super();
	}

	public Serializable generate(SessionImplementor session, Object obj) {
		if (obj instanceof ClassObject) {
			ClassObject clsObj = (ClassObject) obj;
			String objId = clsObj.getObjId();
			if (objId != null && objId.length() != 0)
				return objId;
		}
		return super.generate(session, obj);
	}
}

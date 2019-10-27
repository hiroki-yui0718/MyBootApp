package com.tuyano.springboot;

import java.util.List;

import com.tuyano.springboot.model.MsgData;

public interface MsgDataDao<T> {
	public List<MsgData> getAll();
	public MsgData findById(long id);
}

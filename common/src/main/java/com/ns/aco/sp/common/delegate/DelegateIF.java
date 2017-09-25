package com.ns.aco.sp.common.delegate;

public interface DelegateIF {

	@SuppressWarnings("hiding")
	public interface Delegate<R> {
	    public R invoke();
	}
	
	@SuppressWarnings("hiding")
	public interface Delegate1<T, R> {
	    public R invoke(T arg);
	}
	
	@SuppressWarnings("hiding")
	public interface Delegate2<T1, T2, R> {
	    public R invoke(T1 arg1, T2 arg2);
	}
	
	@SuppressWarnings("hiding")
	public interface Delegate3<T1, T2, T3, R> {
	    public R invoke(T1 arg1, T2 arg2, T3 arg3);
	}
}

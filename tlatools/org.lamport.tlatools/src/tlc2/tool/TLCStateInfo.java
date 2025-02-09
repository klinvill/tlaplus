// Copyright (c) 2003 Compaq Corporation.  All rights reserved.
// Portions Copyright (c) 2003 Microsoft Corporation.  All rights reserved.
// Last modified on Mon 30 Apr 2007 at 13:18:35 PST by lamport 
//      modified on Sat Feb 17 12:07:55 PST 2001 by yuanyu 

package tlc2.tool;

public class TLCStateInfo {
  public static final String INITIAL_PREDICATE = "<Initial predicate>";
  
  public TLCStateInfo predecessorState;
  public long stateNumber;
  public final TLCState state;
  public Object info;
  public Long fp;

	public TLCStateInfo(TLCState initialState) {
		this.state = initialState;
		this.info = INITIAL_PREDICATE;
		this.stateNumber = 1;
		this.fp = initialState.fingerPrint();
	}

  public TLCStateInfo(TLCState s, Object info) {
    this.state = s;
    this.info = info;
  }
  
  public TLCStateInfo(TLCState state, int stateOrdinal) {
	  this.state = state;
	  this.stateNumber = stateOrdinal;
	  this.info = "";
  }

  public TLCStateInfo(TLCState s, String info, int stateNum) {
	  this(s, info);
	  stateNumber = stateNum;
  }

  public TLCStateInfo(TLCState s, String info, int stateNum, long fp) {
	  this(s, info);
	  stateNumber = stateNum;
	  this.fp = fp;
  }
  
  public TLCStateInfo(TLCState s, TLCStateInfo info) {
	  this(s, info.info);
	  this.stateNumber = info.stateNumber;
	  this.fp = info.fp;
  }

  public final long fingerPrint() {
	  if (fp == null) {
		  fp = this.state.fingerPrint();
	  }
	  return fp.longValue();
  }

  public final String toString() {
    return this.state.toString();
  }
  
  public boolean equals(Object other) {
	  if (other instanceof TLCStateInfo) {
		  TLCStateInfo sinfo = (TLCStateInfo) other;
		  return this.state.equals(sinfo.state);
	  } else if (other instanceof TLCState) {
		  TLCState state = (TLCState) other;
		  return this.state.equals(state);
	  }
	  return false;
  }

  public int hashCode() {
	  return this.state.hashCode();
  }

  public TLCState getOriginalState() {
	return state;
  }
}

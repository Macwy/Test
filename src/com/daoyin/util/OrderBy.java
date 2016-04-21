package com.daoyin.util;

public enum OrderBy {
	ASC {
		@Override
		public String getValue() {
			return "asc";
		}
	},
	DESC {
		@Override
		public String getValue() {
			return "desc";
		}
	};
	public abstract String getValue();
}

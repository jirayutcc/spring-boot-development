package com.spring.development.enums;

public class SizeEnum {

	public enum Size {
		SMALL, MEDIUM, LARGE, EXTRA_LARGE;

		public String getSize() {
			return switch (this) {
				case SMALL -> "small";
				case MEDIUM -> "medium";
				case LARGE -> "large";
				case EXTRA_LARGE -> "extra large";
				default -> null;
			};
		}
	}
}

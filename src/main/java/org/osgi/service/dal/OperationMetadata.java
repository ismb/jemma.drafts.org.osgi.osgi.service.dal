package org.osgi.service.dal;

import java.util.Map;

public interface OperationMetadata {
	public static final String META_INFO_DESCRIPTION = "description";

	Map getMedata();

	PropertyMetadata[] getParametersMetadata();

	PropertyMetadata getReturnValueMetadata();
}

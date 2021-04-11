package com.fms.farm.controller;

public class ControllerLinks {
	public final static String HELLO_FARM = "/hello";
	public final static String UPLOAD_FILE = "/upload";
	public final static String ADD_TEST_DATA_VIA_API = "/uploadTestDataViaApi";
	public final static String UPLOAD_TEST_CSV_DATA_VIA_API = "/uploadTestFile";
	public final static String UPLOAD_JOBS = "/uploadjobs";
	public final static String UPLOAD_DEGREE_DAY = "/uploaddegreeday";
	public final static String DEGREE_DAY = "/degreeDay";
	public final static String ID = "/id";
	public final static String ALL = "/all";
	public final static String CUSTOM = "/custom";
	public final static String GET = "/get";
	public final static String CREATE = "/create";
	public final static String GET_ALL_DEGREE_DAY_FOR_REGIION = DEGREE_DAY + GET;
	public final static String UPLOAD_STRESS_MODEL = "/stressModel/upload";
	public final static String GET_ALL_STRESS_MODEL = "/stressModel/get/{region}";
}

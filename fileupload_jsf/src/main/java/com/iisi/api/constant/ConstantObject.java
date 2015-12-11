package com.iisi.api.constant;

public interface ConstantObject {
	
	/** ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓	警告訊息   ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ */
	
	/**
	 * 警告訊息-請輸入資料
	 */
	public static final String INPUT_DATA = "請輸入資料";
	
	/**
	 * 警告訊息-請重新再輸入資料
	 */
	public static final String AGAIN_INPUT_DATA = "請重新再輸入資料";
	
	/**
	 * 警告訊息-請輸入使用者帳號
	 */
	public static final String WARN_MSG_INPUT_USER_ID = "請輸入使用者帳號";
		
	/**
	 * 警告訊息-請輸入密碼
	 */
	public static final String WARN_MSG_INPUT_USER_PWD = "請輸入密碼";
		
	/**
	 * 警告訊息-使用者帳號錯誤
	 */
	public static final String WARN_MSG_USER_ERR = "使用者帳號錯誤";
	
	/**
	 * 警告訊息-密碼輸入錯誤
	 */
	public static final String WARN_MSG_USER_PWD_ERR = "密碼輸入錯誤";
	
	/**
	 * 警告訊息-請輸入起始日
	 */
	public static final String WARN_MSG_INPUT_START_DATE = "請輸入起始日";
	
	/**
	 * 警告訊息-請輸入迄止日
	 */
	public static final String WARN_MSG_INPUT_END_DATE = "請輸入迄止日";
	
	/**
	 * 警告訊息-請輸入日期
	 */
	public static final String WARN_MSG_INPUT_UPLOAD_DATE = "請輸入日期";
	
	/**
	 * 警告訊息-請輸入類型選項
	 */
	public static final String WARN_MSG_INPUT_TYPE = "請輸入類型選項";
	
	/**
	 * 警告訊息-請輸入密件選項
	 */
	public static final String WARN_MSG_INPUT_SECRET = "請輸入密件選項";
	
	/**
	 * 警告訊息-請輸入分類號
	 */
	public static final String WARN_MSG_INPUT_CLASSNUM = "請輸入分類號";
	
	/**
	 * 警告訊息-請輸入日期
	 */
	public static final String WARN_MSG_INPUT_DATE = "請輸入日期";
		
	/**
	 * 警告訊息-請輸入公文文號
	 */
	public static final String WARN_MSG_INPUT_DISPATCHNUM = "請輸入公文文號";
	
	/**
	 * 警告訊息-請輸入主旨
	 */
	public static final String WARN_MSG_INPUT_SUBJECT = "請輸入主旨";
	
	/**
	 * 警告訊息-請輸入檔案
	 */
	public static final String WARN_MSG_INPUT_FILE = "請輸入檔案";
	
	/**
	 * 警告訊息-請輸入舊密碼
	 */
	public static final String WARN_MSG_INPUT_OLD_PASSWORD = "請輸入舊密碼";
	
	/**
	 * 警告訊息-請輸入新密碼
	 */
	public static final String WARN_MSG_INPUT_NEW_PASSWORD = "請輸入新密碼";
	
	/**
	 * 警告訊息-請輸入確認密碼
	 */
	public static final String WARN_MSG_INPUT_CONFIRM_PASSWORD = "請輸入確認密碼";
	
	/**
	 * 警告訊息-新舊密碼請勿相同
	 */
	public static final String WARN_MSG_INPUT_OLD_NEW_PASSWORD = "新舊密碼請勿相同";
	
	/**
	 * 警告訊息-新密碼與確認密碼欄位不同
	 */
	public static final String WARN_MSG_INPUT_NEW_CONFIRM_PASSWORD = "新密碼與確認密碼欄位不同";
	
	/**
	 * 警告訊息-請輸入科別
	 */
	public static final String WARN_MSG_INPUT_OFFICE = "請輸入科別";
	
	/**
	 * 警告訊息-請輸入使用者姓名
	 */
	public static final String WARN_MSG_INPUT_USERNAME = "請輸入使用者姓名";
	
	/**
	 * 警告訊息-請輸入在職狀態
	 */
	public static final String WARN_MSG_INPUT_STATE = "在職狀態";
	
	/**
	 * 警告訊息-請輸入使用者權限
	 */
	public static final String WARN_MSG_INPUT_ROLEID = "請輸入使用者權限";
	
		
	/** ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑	警告訊息   ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */
	
	/** ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓	錯誤訊息   ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ */
		
	/**
	 * 錯誤訊息-資料已存在
	 */
	public static final String ERROR_MSG_DATA = "資料已存在";
	
	/**
	 * 錯誤訊息-使用者資料已存在
	 */
	public static final String ERROR_MSG_USER_EXIST = "使用者資料已存在";
	
	/**
	 * 錯誤訊息-使用者資料不存在
	 */
	public static final String ERROR_MSG_USER_NOT_EXIST = "使用者資料不存在";
	
	/**
	 * 錯誤訊息-新增使用者資料失敗
	 */
	public static final String ERROR_MSG_USER_ERROR = "新增使用者資料失敗";
	
	/**
	 * 錯誤訊息-檔案不存在
	 */
	public static final String ERROR_MSG_FILE_NOT_EXIST = "檔案不存在";
	
	/**
	 * 錯誤訊息-輸入錯誤
	 */
	public static final String ERROR_INPUT = "輸入錯誤";
	
	/**
	 * 錯誤訊息-使用者帳號和密碼有誤
	 */
	public static final String ERROR_INPUT_USER_PASSWORD = "使用者帳號和密碼有誤";
	
	/**
	 * 錯誤訊息-使用者登入失敗
	 */
	public static final String ERROR_USER_LOGIN = "使用者登入失敗";
	
	
	/** ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑	錯誤訊息   ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */
	
	/** ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓	成功訊息   ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ */
	
	/**
	 * 成功訊息-新增資料成功
	 */
	public static final String INFO_MSG_DATA = "新增資料成功";
	
	/**
	 * 成功訊息-新增使用者資料成功
	 */
	public static final String INFO_MSG_ADD_USER_DATA = "新增使用者資料成功";
		
	/**
	 * 成功訊息-更新使用者密碼成功
	 */
	public static final String INFO_MSG_UPDATE_USER_PASSWORD = "更新使用者密碼成功";
	
	/**
	 * 成功訊息-更新使用者資料成功
	 */
	public static final String INFO_MSG_UPDATE_USER_DATA = "更新使用者資料成功";
	
	/**
	 * 成功訊息-更新成功
	 */
	public static final String INFO_MSG_UPDATE_SUCCESS = "更新成功";
	
	/** ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑	成功訊息   ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */
	
	/** ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓	共用文字   ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ */
	
	/**
	 * 共用文字-字串-1
	 */
	public static final String ZERO = "0";
	
	/**
	 * 共用文字-字串-1
	 */
	public static final String ONE = "1";
	
	/**
	 * 共用文字-字串-2
	 */
	public static final String TWO = "2";
	
	/**
	 * 共用文字-字串-3
	 */
	public static final String THREE = "3";
	
	/**
	 * 共用文字-字串-4
	 */
	public static final String FOUR = "4";
	
	/**
	 * 共用文字-字串-5
	 */
	public static final String FIVE = "5";
	
	/**
	 * 共用文字-字串-6
	 */
	public static final String SIX = "6";
	
	/**
	 * 共用文字-字串-7
	 */
	public static final String SEVEN = "7";
	
	/**
	 * 共用文字-字串-8
	 */
	public static final String EIGHT = "8";
	
	/**
	 * 共用文字-字串-9
	 */
	public static final String NINE = "9";
	
	/**
	 * 共用文字-字串-大寫-A
	 */
	public static final String UPPER_CASE_A = "A";
	
	/**
	 * 共用文字-字串-大寫-B
	 */
	public static final String UPPER_CASE_B = "B";

	/**
	 * 共用文字-字串-大寫-C
	 */
	public static final String UPPER_CASE_C = "C";

	/**
	 * 共用文字-字串-大寫-D
	 */
	public static final String UPPER_CASE_D = "D";

	/**
	 * 共用文字-字串-大寫-E
	 */
	public static final String UPPER_CASE_E = "E";

	/**
	 * 共用文字-字串-大寫-F
	 */
	public static final String UPPER_CASE_F = "F";

	/**
	 * 共用文字-字串-大寫-G
	 */
	public static final String UPPER_CASE_G = "G";

	/**
	 * 共用文字-字串-大寫-H
	 */
	public static final String UPPER_CASE_H = "H";

	/**
	 * 共用文字-字串-大寫-I
	 */
	public static final String UPPER_CASE_I = "I";

	/**
	 * 共用文字-字串-大寫-J
	 */
	public static final String UPPER_CASE_J = "J";

	/**
	 * 共用文字-字串-大寫-K
	 */
	public static final String UPPER_CASE_K = "K";

	/**
	 * 共用文字-字串-大寫-L
	 */
	public static final String UPPER_CASE_L = "L";

	/**
	 * 共用文字-字串-大寫-M
	 */
	public static final String UPPER_CASE_M = "M";

	/**
	 * 共用文字-字串-大寫-N
	 */
	public static final String UPPER_CASE_N = "N";

	/**
	 * 共用文字-字串-大寫-O
	 */
	public static final String UPPER_CASE_O = "O";

	/**
	 * 共用文字-字串-大寫-P
	 */
	public static final String UPPER_CASE_P = "P";

	/**
	 * 共用文字-字串-大寫-Q
	 */
	public static final String UPPER_CASE_Q = "Q";

	/**
	 * 共用文字-字串-大寫-R
	 */
	public static final String UPPER_CASE_R = "R";

	/**
	 * 共用文字-字串-大寫-S
	 */
	public static final String UPPER_CASE_S = "S";

	/**
	 * 共用文字-字串-大寫-T
	 */
	public static final String UPPER_CASE_T = "T";

	/**
	 * 共用文字-字串-大寫-U
	 */
	public static final String UPPER_CASE_U = "U";

	/**
	 * 共用文字-字串-大寫-V
	 */
	public static final String UPPER_CASE_V = "V";

	/**
	 * 共用文字-字串-大寫-W
	 */
	public static final String UPPER_CASE_W = "W";

	/**
	 * 共用文字-字串-大寫-X
	 */
	public static final String UPPER_CASE_X = "X";

	/**
	 * 共用文字-字串-大寫-Y
	 */
	public static final String UPPER_CASE_Y = "Y";

	/**
	 * 共用文字-字串-大寫-Z
	 */
	public static final String UPPER_CASE_Z = "Z";
	
	/**
	 * 共用文字-字串-小寫-a
	 */
	public static final String LOWER_CASE_A = "a";
	
	/**
	 * 共用文字-字串-小寫-b
	 */
	public static final String LOWER_CASE_B = "b";

	/**
	 * 共用文字-字串-小寫-c
	 */
	public static final String LOWER_CASE_C = "c";

	/**
	 * 共用文字-字串-小寫-d
	 */
	public static final String LOWER_CASE_D = "d";

	/**
	 * 共用文字-字串-小寫-e
	 */
	public static final String LOWER_CASE_E = "e";

	/**
	 * 共用文字-字串-小寫-f
	 */
	public static final String LOWER_CASE_F = "f";

	/**
	 * 共用文字-字串-小寫-g
	 */
	public static final String LOWER_CASE_G = "g";

	/**
	 * 共用文字-字串-小寫-h
	 */
	public static final String LOWER_CASE_H = "h";

	/**
	 * 共用文字-字串-小寫-i
	 */
	public static final String LOWER_CASE_I = "i";

	/**
	 * 共用文字-字串-小寫-j
	 */
	public static final String LOWER_CASE_J = "j";

	/**
	 * 共用文字-字串-小寫-k
	 */
	public static final String LOWER_CASE_K = "k";

	/**
	 * 共用文字-字串-小寫-l
	 */
	public static final String LOWER_CASE_L = "l";

	/**
	 * 共用文字-字串-小寫-m
	 */
	public static final String LOWER_CASE_M = "m";

	/**
	 * 共用文字-字串-小寫-n
	 */
	public static final String LOWER_CASE_N = "n";

	/**
	 * 共用文字-字串-小寫-o
	 */
	public static final String LOWER_CASE_O = "o";

	/**
	 * 共用文字-字串-小寫-p
	 */
	public static final String LOWER_CASE_P = "p";

	/**
	 * 共用文字-字串-小寫-q
	 */
	public static final String LOWER_CASE_Q = "q";

	/**
	 * 共用文字-字串-小寫-r
	 */
	public static final String LOWER_CASE_R = "r";

	/**
	 * 共用文字-字串-小寫-s
	 */
	public static final String LOWER_CASE_S = "s";

	/**
	 * 共用文字-字串-小寫-t
	 */
	public static final String LOWER_CASE_T = "t";

	/**
	 * 共用文字-字串-小寫-u
	 */
	public static final String LOWER_CASE_U = "u";

	/**
	 * 共用文字-字串-小寫-v
	 */
	public static final String LOWER_CASE_V = "v";

	/**
	 * 共用文字-字串-小寫-w
	 */
	public static final String LOWER_CASE_W = "w";

	/**
	 * 共用文字-字串-小寫-x
	 */
	public static final String LOWER_CASE_X = "x";

	/**
	 * 共用文字-字串-小寫-y
	 */
	public static final String LOWER_CASE_Y = "y";

	/**
	 * 共用文字-字串-小寫-z
	 */
	public static final String LOWER_CASE_Z = "z";
	
	/** ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑	共用文字   ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ */
}

package com.rahul.library.exceptions;

public class GlobalErrorCode {

	public static final Long ERROR_ENTITY_NOT_FOUND = 1000L;
    public static final Long ERROR_MEMBER_ALREADY_REGISTERED = 1001L;
    public static final Long ERROR_MEMBER_ALREADY_BORROWED=1002L;
    
    //Entity not Found
    public static final Long ERROR_BOOK_ID_NOT_FOUND=2000L;
    public static final Long ERROR_BOOK__ISBN_NOT_FOUND=2001L;
    public static final Long ERROR_AUTHOR_ID_NOT_FOUND =2002L;
    public static final Long ERROR_MEMBER_ID_NOT_FOUND =2003L;
    
    //Member Not Active
    public static final Long ERROR_MEMBER_ID_NOT_ACTIVE =3000L;
    
    
}

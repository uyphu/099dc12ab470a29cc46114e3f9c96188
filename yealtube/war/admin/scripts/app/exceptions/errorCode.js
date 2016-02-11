/**
 * errorCode.js
 * Author: Phu
 * Date: Apr 11, 2015
 */

'use strict';

/**
 * Constructor.
 */
function ErrorCode() {}

/** The Constant ERROR_INIT_ENDPOINT_SERVICE. */
ErrorCode.ERROR_INIT_ENDPOINT_SERVICE = 'Error initilize cloud service.';

/** The Constant ERROR_OAUTH2_SERVICE. */
ErrorCode.ERROR_OAUTH2_SERVICE = 'Error authorize oauth2 service.';

/** The Constant ERROR_AUTHORIZE_SERVICE. */
ErrorCode.ERROR_AUTHORIZE_SERVICE = 'Error authorize service.';

/** The Constant ERROR_CALL_ENDPOINT_SERVICE. */
ErrorCode.ERROR_CALL_ENDPOINT_SERVICE = 'Error call cloud service.';

/** The error duplicated name. */
ErrorCode.ERROR_DUPLICATED_NAME = '[600] Name already exists'; 

/** The error duplicated email. */
ErrorCode.ERROR_DUPLICATED_EMAIL = '[601] Email already exists'; 

/** The error exist object. */
ErrorCode.ERROR_EXIST_OBJECT = '[602] Object already exists';

/** The error record not found. */
ErrorCode.ERROR_RECORD_NOT_FOUND = '[603] Object not found';

/** The error parse query. */
ErrorCode.ERROR_PARSE_QUERY = '[604] Parsing query string error';

/** The error remove entity. */
ErrorCode.ERROR_REMOVE_ENTITY = '[605] Remove entity error';

/** The error insert entity. */
ErrorCode.ERROR_INSERT_ENTITY = '[606] Insert entity error';

/** The error update entity. */
ErrorCode.ERROR_UPDATE_ENTITY = '[607] Update entity error';

/** The error conflict login. */
ErrorCode.ERROR_CONFLICT_LOGIN = '[608] Login already exists';

/** The error conflict email. */
ErrorCode.ERROR_CONFLICT_EMAIL = '[609] Email already exists';

/** The error conflict login and email. */
ErrorCode.ERROR_CONFLICT_LOGIN_AND_EMAIL = '[610] Login and Email already exists';

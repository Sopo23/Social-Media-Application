const api = "http://localhost:9090/twitter"; 
const authUrl = api + "/auth";
const sign_in_Url = authUrl + "/sign-in"
const sign_up_Url = authUrl + "/sign-up"
const CLIENT = api + "/clients";
const POSTARE = api + "/posts";
const ID_STRING = "/{id}";
const UPDATE = "/update" + ID_STRING;
const DELETE = "/delete" + ID_STRING;
const FOLLOW = "/followAction";
const SHOW_FOLLOWERS = "/followers" + ID_STRING;
const SHOW_FOLLOWING = "/following" + ID_STRING;
const EDIT = "/edit" + ID_STRING;
const CREATE_POST = "/create";
const LIKE = "/like" + ID_STRING;
const REPLY = "/replys" + ID_STRING;
const VIEW_PRIVACY = "/view" + ID_STRING;
const SHOW_ALL = "/show";
const SHOW_POST = "/without-parent";
const CLIENT_DATA = "/client_data" + ID_STRING;
const SHOW_REPLYS = "/replys" + ID_STRING;


const enviroment = {
    CLIENT_DATA:CLIENT_DATA,
    SHOW_POST:SHOW_POST,
    SHOW_ALL:SHOW_ALL,
    api: api,
    authUrl: authUrl,
    sign_in_Url : sign_in_Url,
    sign_up_Url : sign_up_Url,
    CLIENT : CLIENT,
    POSTARE : POSTARE,
    UPDATE : UPDATE,
    DELETE : DELETE,
    FOLLOW : FOLLOW,
    SHOW_FOLLOWERS : SHOW_FOLLOWERS,
    SHOW_FOLLOWING : SHOW_FOLLOWING,
    EDIT : EDIT,
    CREATE_POST : CREATE_POST,
    LIKE : LIKE,
    REPLY : REPLY, 
    VIEW_PRIVACY : VIEW_PRIVACY,
    SHOW_REPLYS:SHOW_REPLYS
}

export default enviroment
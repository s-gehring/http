package http.v1_1;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Patterns {

    public final String SP            = " ";
    public final String HT            = "\\t";
    public final String CR            = "\\r";
    public final String LF            = "\\n";
    public final String CRLF          = CR + LF;
    public final String TOKEN         = "[\\x21\\x23-\\x27\\x2a-\\x2b\\x2d\\x2e\\x30\\x39\\x41-\\x5a\\x5e-\\x7a\\x7c\\x7e]"
            + "+";
    public final String OCTET         = "[\\x00-\\xff]";
    public final String LWS           = "(" + CRLF + ")?(" + SP + "|" + HT + ")+";
    public final String SEPARATORS    = "\\(|"
            + "\\)|"
            + "<|"
            + ">|"
            + "@|"
            + "\\,|"
            + ";|"
            + ":|"
            + "\\\\|"
            + "\\\"|"
            + "/|"
            + "\\[|"
            + "\\]|"
            + "\\?|"
            + "=|"
            + "\\{|"
            + "\\}|"
            + SP + "|"
            + HT;
    public final String CHAR          = "[\\x00-\\x7f]";
    public final String DIGIT         = "[0-9]";
    public final String ALPHA         = "[A-Za-z]";
    public final String HEXDIG        = "[A-Fa-f0-9]";
    public final String TEXT          = "[\\x32-\\x7e]";
    public final String SUB_DELIMS    = "!|$|&|'|\\(|\\)|\\*|\\+|,|;|=";
    public final String GEN_DELIMS    = ":|/|\\?|#|\\[|\\]|@";
    public final String RESERVED      = GEN_DELIMS + "|" + SUB_DELIMS;
    public final String UNRESERVED    = ALPHA + "|" + DIGIT + "-|\\.|_|\\~";
    public final String PCT_ENCODED   = "%" + HEXDIG + HEXDIG;
    public final String PCHAR         = UNRESERVED + "|" + PCT_ENCODED + "|" + SUB_DELIMS
            + "|:|@";
    public final String FRAGMENT      = "(" + PCHAR + "|/|\\?)*";
    public final String QUERY         = "(" + PCHAR + "|/|\\?)*";
    public final String SEGMENT_NZ_NC = "(" + UNRESERVED + PCT_ENCODED + SUB_DELIMS + "@)+";
    public final String SEGMENT_NZ    = PCHAR + "+";
    public final String SEGMENT       = PCHAR + "*";
    public final String PATH_EMPTY    = "";
    public final String PATH_ROOTLESS = SEGMENT_NZ + "(/" + SEGMENT + ")*";
    public final String PATH_NOSCHEME = SEGMENT_NZ_NC + "(/" + SEGMENT + ")*";
    public final String PATH_ABSOLUTE = "/(" + SEGMENT_NZ + "(/" + SEGMENT + ")*" + ")?";
    public final String PATH_ABEMPTY  = "(/" + SEGMENT + ")*";
    public final String PATH          = PATH_ABEMPTY + "|" + PATH_ABSOLUTE + "|"
            + PATH_NOSCHEME
            + "|"
            + PATH_ROOTLESS + "|" + PATH_EMPTY;
    public final String REG_NAME      = "(" + UNRESERVED + "|" + PCT_ENCODED + "|"
            + SUB_DELIMS
            + ")*";
    public final String DEC_OCTET     = DIGIT + "|" +
            "([1-9]" + DIGIT + ")|" +
            "([1-9]" + DIGIT + DIGIT + ")|" +
            "(2[0-4][0-9])|" +
            "(25[0-5])";

    public final String IPV4ADDRESS = DEC_OCTET + "\\." + DEC_OCTET + "\\." + DEC_OCTET
            + "\\."
            + DEC_OCTET;
    /**
     * @see <a href=
     *      "https://stackoverflow.com/questions/53497/regular-expression-that-matches-valid-ipv6-addresses/1934546">Stackoverflow</a>
     */
    public final String IPV6ADDRESS = "(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))";
    public final String IPVFUTURE   = "v" + HEXDIG + "+\\." + "(" + UNRESERVED + "|"
            + SUB_DELIMS + "|:)+";
    public final String IP_LITERAL  = "\\[(" + IPV6ADDRESS + "|" + IPVFUTURE + ")\\]";
    public final String PORT        = DIGIT + "*";
    public final String HOST        = IP_LITERAL + "|" + IPV4ADDRESS + "|" + REG_NAME;
    public final String USERINFO    = "(" + UNRESERVED + "|" + PCT_ENCODED + "|" + SUB_DELIMS
            + "|:)*";
    public final String AUTHORITY   = "(" + USERINFO + "@)?" + HOST + "(:" + PORT + ")?";
    public final String SCHEME      = ALPHA + "("
            + ALPHA + "|"
            + DIGIT + "|"
            + "\\+|"
            + "-|"
            + "\\.)";

    public final String RELATIVE_PART = "//" + AUTHORITY + "("
            + PATH_ABEMPTY + "|"
            + PATH_ABSOLUTE + "|"
            + PATH_NOSCHEME + "|"
            + PATH_EMPTY + ")";

    public final String HIER_PART = "//"
            + AUTHORITY + "("
            + PATH_ABEMPTY + "|"
            + PATH_ABSOLUTE + "|"
            + PATH_ROOTLESS + "|"
            + PATH_EMPTY + ")";

    public final String URI = SCHEME + ":" + HIER_PART + Patterns.COMPLETE_QUERY
            + Patterns.COMPLETE_FRAGMENT;

    public final String RELATIVE_REF  = RELATIVE_PART + Patterns.COMPLETE_QUERY
            + Patterns.COMPLETE_FRAGMENT;
    public final String ABSOLUTE_URI  = SCHEME + ":" + HIER_PART + Patterns.COMPLETE_QUERY;
    public final String URI_REFERENCE = URI + "|" + RELATIVE_REF;

    public final String PATH_SEGMENTS = SEGMENT + "(/" + SEGMENT + ")*";
    public final String ABS_PATH      = "/" + PATH_SEGMENTS;

    public final String REQUEST_URI  = "\\*|(" + ABSOLUTE_URI + ")|(" + ABS_PATH + ")|("
            + AUTHORITY + ")";
    public final String HTTP_VERSION = "HTTP/(" + DIGIT + "+)\\.(" + DIGIT + "+)";

    public final String METHOD       = "(OPTIONS|GET|HEAD|POST|PUT|DELETE|TRACE|CONNECT)|(" + TOKEN
            + ")";
    public final String REQUEST_LINE = "(" + METHOD + ")" + SP + "(" + REQUEST_URI + ")" + SP + "("
            + HTTP_VERSION + ")" + CRLF;

    public final String DELTA_SECONDS = DIGIT + "+";
    public final String QDTEXT        = "[^\"]*";
    public final String QUOTED_PAIR   = "\\\\" + CHAR;
    public final String QUOTED_STRING = "\"((" + QDTEXT + ")|(" + QUOTED_PAIR + "))\"";

    public final String FIELD_NAME = TOKEN;

    public final String CACHE_EXTENSION          = TOKEN + "(=((" + TOKEN + ")|(" + QUOTED_STRING
            + ")))?";
    public final String CACHE_REQUEST_DIRECTIVE  = "(no-cache)|"
            + "(no-store)|"
            + "(max-age=(" + DELTA_SECONDS + "))|"
            + "(max-stale(=" + DELTA_SECONDS + ")?)|"
            + "(min-fresh=(" + DELTA_SECONDS + "))|"
            + "(no-transform)|"
            + "(only-if-cached)|"
            + "(" + CACHE_EXTENSION + ")";
    public final String CACHE_RESPONSE_DIRECTIVE = "(public)|"
            + "(private(=\"" + Patterns.ALO_FIELD_NAME + "\")?)|"
            + "(no-cache(=\"" + Patterns.ALO_FIELD_NAME + "\")?)|"
            + "(no-store)|"
            + "(no-transform)|"
            + "(must-revalidate)|"
            + "(proxy-revalidate)|"
            + "(max-age=(" + DELTA_SECONDS + "))|"
            + "(s-maxage=(" + DELTA_SECONDS + "))|"
            + "(" + CACHE_EXTENSION + ")";
    public final String CACHE_DIRECTIVE          = "("
            + CACHE_REQUEST_DIRECTIVE + ")|("
            + CACHE_RESPONSE_DIRECTIVE + ")";

    public final String CACHE_CONTROL = "Cache-Control:(" + CACHE_DIRECTIVE + ")(\\,"
            + CACHE_DIRECTIVE
            + ")*";

    public final String CONNECTION_TOKEN = TOKEN;
    public final String CONNECTION       = "Connection:" + Patterns.OPTIONAL_LWS + "("
            + CONNECTION_TOKEN
            + ")(" + Patterns.OPTIONAL_LWS + "," + Patterns.OPTIONAL_LWS + CONNECTION_TOKEN + ")*";

    public final String TIME    = "(" + DIGIT + "){2}:" + "(" + DIGIT + "){2}:" + "(" + DIGIT
            + "){2}";
    public final String WKDAY   = "Mon|Tue|Wed|Thu|Fri|Sat|Sun";
    public final String WEEKDAY = "Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday";
    public final String MONTH   = "Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec";

    public final String DATE1 = "(" + DIGIT + "){2}" + SP + MONTH + SP + "(" + DIGIT + "){4}";
    public final String DATE2 = "(" + DIGIT + "){2}-" + MONTH + "-" + "(" + DIGIT + "){2}";
    public final String DATE3 = MONTH + SP + "(((" + DIGIT + "){2})|(" + SP + DIGIT + "))";

    public final String ASCTIME_DATE = WKDAY + SP + DATE3 + SP + TIME + SP + "(" + DIGIT + "){4}";
    public final String RFC850_DATE  = WEEKDAY + "," + SP + DATE2 + SP + TIME + SP + "GMT";
    public final String RFC1123_DATE = WKDAY + "," + SP + DATE1 + SP + TIME + SP + "GMT";
    public final String HTTP_DATE    = "(" + RFC1123_DATE + ")|(" + RFC850_DATE + ")|("
            + ASCTIME_DATE
            + ")";
    public final String DATE         = "Date:" + HTTP_DATE;

    public final String EXTENSION_PRAGMA = TOKEN + "(=((" + TOKEN + ")|(" + QUOTED_STRING + ")))?";
    public final String PRAGMA_DIRECTIVE = "(no-cache)|(" + EXTENSION_PRAGMA + ")";
    public final String PRAGMA           = "Pragma:" +
            Patterns.OPTIONAL_LWS + "(" +
            PRAGMA_DIRECTIVE + ")(" + Patterns.OPTIONAL_LWS + "," + Patterns.OPTIONAL_LWS
            + PRAGMA_DIRECTIVE + ")*";

    public final String TRAILER = "Trailer:" + Patterns.ALO_FIELD_NAME;

    public final String ATTRIBUTE          = TOKEN;
    public final String VALUE              = "(" + TOKEN + ")|(" + QUOTED_STRING + ")";
    public final String PARAMETER          = "(" + ATTRIBUTE + ")=(" + VALUE + ")";
    public final String TRANSFER_EXTENSION = TOKEN + "(;" + PARAMETER + ")*";
    public final String TRANSFER_CODING    = "(chunked)|(" + TRANSFER_EXTENSION + ")";
    public final String TRANSFER_ENCODING  = "Transfer-Encoding:" + Patterns.ALO_TRANSFER_CODING;

    public final String PRODUCT_VERSION = TOKEN;
    public final String PRODUCT         = TOKEN + "(/(" + PRODUCT_VERSION + "))?";

    public final String UPGRADE = "Upgrade:" + Patterns.ALO_PRODUCT;

    public final String PROTOCOL_NAME    = TOKEN;
    public final String PROTOCOL_VERSION = TOKEN;
    public final String PSEUDONYM        = TOKEN;

    /*
     * Not entirely correct as the RFC allows for a recursive regex here:
     * https://tools.ietf.org/html/rfc2616#section-2.2
     */
    public final String COMMENT           = "\\((.+)\\)";
    public final String RECEIVED_BY       = "(" + HOST + "(:" + PORT + ")?)|(" + PSEUDONYM + ")";
    public final String RECEIVED_PROTOCOL = "((" + PROTOCOL_NAME + ")/)?(" + PROTOCOL_VERSION + ")";
    public final String VIA               = "Via:"
            + Patterns.ALO_RECEIVED_PROTOCOL_RECEIVED_BY_COMMENT;

    public final String WARN_CODE     = "(" + DIGIT + "){3}";
    public final String WARN_AGENT    = "(" + HOST + "(:" + PORT + ")?)|" + PSEUDONYM;
    public final String WARN_TEXT     = QUOTED_STRING;
    public final String WARN_DATE     = "\"" + HTTP_DATE + "\"";
    public final String WARNING_VALUE = "(" + WARN_CODE + ")" + SP + "(" + WARN_AGENT + ")" + SP
            + "("
            + WARN_TEXT + ")" + "(" + SP + WARN_DATE + ")?";
    public final String WARNING       = "Warning:" + Patterns.ALO_WARNING_VALUE;

    public final String GENERAL_HEADER = "(" + CACHE_CONTROL + ")|"
            + "(" + CONNECTION + ")|"
            + "(" + DATE + ")|"
            + "(" + PRAGMA + ")|"
            + "(" + TRAILER + ")|"
            + "(" + TRANSFER_ENCODING + ")|"
            + "(" + VIA + ")|"
            + "(" + WARNING + ")";

    public final String ACCEPT = "ACCEPT:" + Patterns.ALZ_MEDIA_RANGE_ACCEPT_PARAMS;

    public final String QVALUE           = "(0(\\.(" + DIGIT + "){0,3})?)|"
            + "(1(\\.(0){0,3})?)";
    public final String TYPE             = TOKEN;
    public final String SUBTYPE          = TOKEN;
    public final String MEDIA_RANGE      = "((\\*/\\*)|"
            + "((" + TYPE + ")/\\*)|"
            + "((" + TYPE + ")/(" + SUBTYPE + "))"
            + ""
            + ")(;" + PARAMETER + ")*";
    public final String ACCEPT_EXTENSION = ";" + TOKEN + "(=(" + TOKEN + ")|(" + QUOTED_STRING
            + "))?";
    public final String ACCEPT_PARAMS    = ";q=" + QVALUE + "(" + ACCEPT_EXTENSION + ")*";
    public final String CHARSET          = TOKEN;
    public final String ACCEPT_CHARSET   = "Accept-Charset:" + Patterns.ALO_CHARSET_QVALUE;

    public final String CONTENT_CODING  = TOKEN;
    public final String CODINGS         = "((" + CONTENT_CODING + ")|\\*)";
    public final String ACCEPT_ENCODING = "Accept-Encoding:" + Patterns.ALO_CODINGS_QVALUE;

    public final String LANGUAGE_RANGE  = "(\\*|((" + ALPHA + "){1,8}(-(" + ALPHA + "){0,8})*))";
    public final String ACCEPT_LANGUAGE = "Accept-Language:" + Patterns.ALO_LANGUAGE_RANGE_QVALUE;

    public final String AUTH_SCHEME   = TOKEN;
    public final String AUTH_PARAM    = TOKEN + "=((" + TOKEN + ")|(" + QUOTED_STRING + "))";
    public final String CREDENTIALS   = AUTH_SCHEME + Patterns.ALZ_AUTH_PARAM;
    public final String AUTHORIZATION = "Authorization:(" + CREDENTIALS + ")";

    public final String EXPECT_PARAMS         = ";(" + TOKEN + ")((" + TOKEN + ")|(" + QUOTED_STRING
            + "))?";
    public final String EXPECTATION_EXTENSION = "(" + TOKEN + ")(=((" + TOKEN + ")|("
            + QUOTED_STRING
            + "))(" + EXPECT_PARAMS + ")*)?";
    public final String EXPECTATION           = "(100-continue)|(" + EXPECTATION_EXTENSION + ")";
    public final String EXPECT                = "Expect:" + Patterns.ALO_EXPECTATION;

    public final String ATOM           = TOKEN;
    public final String WORD           = "(" + ATOM + ")|(" + QUOTED_STRING + ")";
    public final String LOCAL_PART     = WORD + "(\\." + WORD + ")*";
    public final String PHRASE         = "(" + WORD + ")+";
    public final String DOMAIN_REF     = TOKEN;
    public final String DTEXT          = "(" + LWS + ")|([\\x00-\\x0c\\x0e-\\x5a\\x5e-\\x7f])";
    public final String DOMAIN_LITERAL = "\\[((" + DTEXT + ")|(" + QUOTED_PAIR + "))*\\]";
    public final String SUB_DOMAIN     = "(" + DOMAIN_REF + ")|(" + DOMAIN_LITERAL + ")";
    public final String DOMAIN         = SUB_DOMAIN + "(\\." + SUB_DOMAIN + ")*";
    public final String ADDR_SPEC      = "(" + LOCAL_PART + ")@(" + DOMAIN + ")";
    public final String ROUTE          = Patterns.ALO_AT_DOMAIN + ":";
    public final String ROUTE_ADDR     = "<(" + ROUTE + ")?(" + ADDR_SPEC + ")>";
    public final String MAILBOX        = "(" + ADDR_SPEC + ")|((" + PHRASE + ")?(" + ROUTE_ADDR
            + "))";
    public final String FROM           = "From:" + MAILBOX;

    public final String OPAQUE_TAG          = QUOTED_STRING;
    public final String ENTITY_TAG          = "(W/)?(" + OPAQUE_TAG + ")";
    public final String IF_MATCH            = "If-Match:(\\*|(" + Patterns.ALO_ENTITY_TAG + "))";
    public final String IF_MODIFIED_SINCE   = "If-Modified-Since:(" + HTTP_DATE + ")";
    public final String IF_NONE_MATCH       = "If-None-Match:(\\*|(" + Patterns.ALO_ENTITY_TAG
            + "))";
    public final String IF_RANGE            = "If-Range:((" + ENTITY_TAG + ")|(" + HTTP_DATE + "))";
    public final String IF_UNMODIFIED_SINCE = "If-Unmodified-Since:(" + HTTP_DATE + ")";
    public final String MAX_FORWARDS        = "Max-Forwards:(" + DIGIT + ")+";
    public final String PROXY_AUTHORIZATION = "Proxy-Authorization:(" + CREDENTIALS + ")";

    public final String SUFFIX_LENGTH          = "(" + DIGIT + ")+";
    public final String SUFFIX_BYTE_RANGE_SPEC = "-(" + SUFFIX_LENGTH + ")";
    public final String BYTES_UNIT             = "bytes";
    public final String LAST_BYTE_POS          = "(" + DIGIT + ")+";
    public final String FIRST_BYTE_POS         = "(" + DIGIT + ")+";
    public final String BYTE_RANGE_SPEC        = "(" + FIRST_BYTE_POS + ")-(" + LAST_BYTE_POS
            + ")?";
    public final String BYTE_RANGE_SET         = Patterns.ALO_BYTE_RANGE_SPEC_SUFFIX_BYTE_RANGE_SPEC;
    public final String BYTE_RANGES_SPECIFIER  = "(" + BYTES_UNIT + ")=(" + BYTE_RANGE_SET + ")";
    public final String RANGES_SPECIFIER       = BYTE_RANGES_SPECIFIER;
    public final String RANGE                  = "Range:(" + RANGES_SPECIFIER + ")";

    public final String NET_PATH     = "//(" + AUTHORITY + ")(" + ABS_PATH + ")?";
    public final String ESCAPED      = "%" + HEXDIG + HEXDIG;
    public final String REL_SEGMENT  = Patterns.ALO_UNRESERVED_ESCAPED_STUFF;
    public final String REL_PATH     = "(" + REL_SEGMENT + ")(" + ABS_PATH + ")?";
    public final String RELATIVE_URI = "((" + NET_PATH + ")|(" + ABS_PATH + ")|(" + REL_PATH
            + "))(\\?" + QUERY + ")?";
    public final String REFERER      = "Referer:((" + ABSOLUTE_URI + ")|(" + RELATIVE_URI
            + "))";

    public final String T_CODINGS = "(trailers)|((" + TRANSFER_EXTENSION + ")(" + ACCEPT_PARAMS
            + ")?)";
    public final String TE        = "TE:(" + Patterns.ALZ_T_CODINGS + ")";

    public final String USER_AGENT = "User-Agent:((" + PRODUCT + ")|(" + COMMENT + "))+";

    public final String REQUEST_HEADER = "(" + ACCEPT + ")|"
            + "(" + ACCEPT_CHARSET + ")|"
            + "(" + ACCEPT_ENCODING + ")|"
            + "(" + ACCEPT_LANGUAGE + ")|"
            + "(" + AUTHORIZATION + ")|"
            + "(" + EXPECT + ")|"
            + "(" + FROM + ")|"
            + "(" + HOST + ")|"
            + "(" + IF_MATCH + ")|"
            + "(" + IF_MODIFIED_SINCE + ")|"
            + "(" + IF_NONE_MATCH + ")|"
            + "(" + IF_RANGE + ")|"
            + "(" + IF_UNMODIFIED_SINCE + ")|"
            + "(" + MAX_FORWARDS + ")|"
            + "(" + PROXY_AUTHORIZATION + ")|"
            + "(" + RANGE + ")|"
            + "(" + REFERER + ")|"
            + "(" + TE + ")|"
            + "(" + USER_AGENT + ")";

    public final String ALLOW = "Allow:(" + Patterns.ALZ_METHOD + ")";

    public final String CONTENT_ENCODING = "Content-Encoding:" + Patterns.ALO_CONTENT_CODING;

    public final String PRIMARY_TAG      = "(" + ALPHA + "){1,8}";
    public final String SUBTAG           = "(" + ALPHA + "){1,8}";
    public final String LANGUAGE_TAG     = "(" + PRIMARY_TAG + ")(-" + SUBTAG + ")*";
    public final String CONTENT_LANGUAGE = "Content-Language:" + Patterns.ALO_LANGUAGE_TAG;

    public final String CONTENT_LENGTH = "Content-Length:(" + DIGIT + ")+";

    public final String CONTENT_LOCATION = "Content-Location:((" + ABSOLUTE_URI + ")|("
            + RELATIVE_URI + "))";
    // https://tools.ietf.org/html/rfc4648#page-5
    public final String MD5_ALPHABET = "[A-Za-z0-9+/=-_]";
    public final String MD5_DIGEST   = "(" + MD5_ALPHABET + ")+";
    public final String CONTENT_MD5  = "Content-MD5:" + MD5_DIGEST;

    public final String BYTE_RANGE_RESP_SPEC    = "((" + FIRST_BYTE_POS + ")-(" + LAST_BYTE_POS
            + "))|\\*";
    public final String INSTANCE_LENGTH         = "(" + DIGIT + ")+";
    public final String BYTE_CONTENT_RANGE_SPEC = "(" + BYTES_UNIT + ")" + SP + "(("
            + BYTE_RANGE_RESP_SPEC + ")/(\\*|(" + INSTANCE_LENGTH + "))";
    public final String CONTENT_RANGE_SPEC      = BYTE_CONTENT_RANGE_SPEC;
    public final String CONTENT_RANGE           = "Content-Range:(" + CONTENT_RANGE_SPEC + ")";

    public final String EXPIRES = "Expires:(" + HTTP_DATE + ")";

    public final String LAST_MODIFIED = "Last-Modified:(" + HTTP_DATE + ")";

    public final String FIELD_CONTENT    = "((" + TEXT + ")|(" + TOKEN + ")|(" + SEPARATORS + ")|("
            + QUOTED_STRING + "))*";
    public final String FIELD_VALUE      = "((" + LWS + ")|(" + FIELD_CONTENT + "))*";
    public final String MESSAGE_HEADER   = "(" + FIELD_NAME + "):(" + FIELD_VALUE + ")?";
    public final String EXTENSION_HEADER = MESSAGE_HEADER;
    public final String ENTITY_HEADER    = "(" + ALLOW + ")|"
            + "(" + CONTENT_ENCODING + ")|"
            + "(" + CONTENT_LANGUAGE + ")|"
            + "(" + CONTENT_LENGTH + ")|"
            + "(" + CONTENT_LOCATION + ")|"
            + "(" + CONTENT_MD5 + ")|"
            + "(" + CONTENT_RANGE + ")|"
            + "(" + EXPIRES + ")|"
            + "(" + LAST_MODIFIED + ")|"
            + "(" + EXTENSION_HEADER + ")";

    public final String ENTITY_BODY    = "(" + OCTET + ")*";
    public final String WEIRD_ENCODING = ".+";
    public final String MESSAGE_BODY   = "(" + ENTITY_BODY + ")|(" + WEIRD_ENCODING + ")";

    /*
     * Private helper variables.
     */
    private final String COMPLETE_QUERY      = "(\\?" + QUERY + ")?";
    private final String COMPLETE_FRAGMENT   = "(#" + FRAGMENT + ")?";
    private final String OPTIONAL_LWS        = "((" + LWS + ")*)";
    private final String ALO_FIELD_NAME      = OPTIONAL_LWS + "(" + FIELD_NAME + ")("
            + OPTIONAL_LWS + "," + OPTIONAL_LWS + FIELD_NAME + ")*";
    private final String ALO_TRANSFER_CODING = OPTIONAL_LWS + "(" + TRANSFER_CODING + ")("
            + OPTIONAL_LWS + "," + OPTIONAL_LWS + TRANSFER_CODING + ")*";
    private final String ALO_PRODUCT         = OPTIONAL_LWS + "(" + PRODUCT + ")("
            + OPTIONAL_LWS + "," + OPTIONAL_LWS + PRODUCT + ")*";

    private final String RECEIVED_PROTOCOL_RECEIVED_BY_COMMENT     = "((" + RECEIVED_PROTOCOL
            + ")(" + RECEIVED_BY + ")(" + COMMENT + ")?)";
    private final String ALO_RECEIVED_PROTOCOL_RECEIVED_BY_COMMENT = OPTIONAL_LWS + "("
            + RECEIVED_PROTOCOL_RECEIVED_BY_COMMENT + ")("
            + OPTIONAL_LWS + "," + OPTIONAL_LWS + RECEIVED_PROTOCOL_RECEIVED_BY_COMMENT + ")*";

    private final String ALO_WARNING_VALUE             = OPTIONAL_LWS + "(" + WARNING_VALUE
            + ")("
            + OPTIONAL_LWS + "," + OPTIONAL_LWS + WARNING_VALUE + ")*";
    private final String MEDIA_RANGE_ACCEPT_PARAMS     = "((" + MEDIA_RANGE + ")("
            + ACCEPT_PARAMS + ")?)";
    private final String ALZ_MEDIA_RANGE_ACCEPT_PARAMS = OPTIONAL_LWS + "("
            + MEDIA_RANGE_ACCEPT_PARAMS + ")?("
            + OPTIONAL_LWS + "," + OPTIONAL_LWS + MEDIA_RANGE_ACCEPT_PARAMS + ")*";
    private final String ALZ_AUTH_PARAM                = OPTIONAL_LWS + "("
            + AUTH_PARAM + ")?("
            + OPTIONAL_LWS + "," + OPTIONAL_LWS + AUTH_PARAM + ")*";

    private final String CHARSET_QVALUE            = "(((" + CHARSET + ")|\\*)(;q="
            + QVALUE + ")?)";
    private final String ALO_CHARSET_QVALUE        = OPTIONAL_LWS + "(" + CHARSET_QVALUE
            + ")("
            + OPTIONAL_LWS + "," + OPTIONAL_LWS + CHARSET_QVALUE + ")*";
    private final String CODINGS_QVALUE            = "((" + CODINGS + ")(;q=" + QVALUE
            + ")?)";
    private final String ALO_CODINGS_QVALUE        = OPTIONAL_LWS + "(" + CODINGS_QVALUE
            + ")("
            + OPTIONAL_LWS + "," + OPTIONAL_LWS + CODINGS_QVALUE + ")*";
    private final String LANGUAGE_RANGE_QVALUE     = "((" + LANGUAGE_RANGE + ")(;q="
            + QVALUE + ")?)";
    private final String ALO_LANGUAGE_RANGE_QVALUE = OPTIONAL_LWS + "("
            + LANGUAGE_RANGE_QVALUE + ")("
            + OPTIONAL_LWS + "," + OPTIONAL_LWS + LANGUAGE_RANGE_QVALUE + ")*";

    private final String ALO_EXPECTATION = OPTIONAL_LWS + "("
            + EXPECTATION + ")("
            + OPTIONAL_LWS + "," + OPTIONAL_LWS + EXPECTATION + ")*";

    private final String ALO_AT_DOMAIN                              = OPTIONAL_LWS + "(@"
            + DOMAIN + ")("
            + OPTIONAL_LWS + "," + OPTIONAL_LWS + "@" + DOMAIN + ")*";
    private final String ALO_ENTITY_TAG                             = OPTIONAL_LWS + "("
            + ENTITY_TAG + ")("
            + OPTIONAL_LWS + "," + OPTIONAL_LWS + ENTITY_TAG + ")*";
    private final String BYTE_RANGE_SPEC_SUFFIX_BYTE_RANGE_SPEC     = "((" + BYTE_RANGE_SPEC + ")|("
            + SUFFIX_BYTE_RANGE_SPEC + "))";
    private final String ALO_BYTE_RANGE_SPEC_SUFFIX_BYTE_RANGE_SPEC = OPTIONAL_LWS + "("
            + BYTE_RANGE_SPEC_SUFFIX_BYTE_RANGE_SPEC + ")("
            + OPTIONAL_LWS + "," + OPTIONAL_LWS + BYTE_RANGE_SPEC_SUFFIX_BYTE_RANGE_SPEC + ")*";
    private final String UNRESERVED_ESCAPED_STUFF                   = "((" + UNRESERVED + ")|("
            + ESCAPED + ")|;|@|&|=|\\+|$|,)";
    private final String ALO_UNRESERVED_ESCAPED_STUFF               = OPTIONAL_LWS + "("
            + UNRESERVED_ESCAPED_STUFF + ")("
            + OPTIONAL_LWS + "," + OPTIONAL_LWS + UNRESERVED_ESCAPED_STUFF + ")*";
    private final String ALZ_T_CODINGS                              = OPTIONAL_LWS + "("
            + T_CODINGS + ")?("
            + OPTIONAL_LWS + "," + OPTIONAL_LWS + T_CODINGS + ")*";
    private final String ALZ_METHOD                                 = OPTIONAL_LWS + "("
            + METHOD + ")?("
            + OPTIONAL_LWS + "," + OPTIONAL_LWS + METHOD + ")*";
    private final String ALO_CONTENT_CODING                         = OPTIONAL_LWS + "("
            + CONTENT_CODING + ")("
            + OPTIONAL_LWS + "," + OPTIONAL_LWS + CONTENT_CODING + ")*";
    private final String ALO_LANGUAGE_TAG                           = OPTIONAL_LWS + "("
            + LANGUAGE_TAG + ")("
            + OPTIONAL_LWS + "," + OPTIONAL_LWS + LANGUAGE_TAG + ")*";
}

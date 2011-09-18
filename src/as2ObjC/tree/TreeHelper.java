package as2ObjC.tree;
import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.tree.Tree;

import flexprettyprint.handlers.AS3_exParser;


public class TreeHelper 
{
	private static Map<Integer, String> typeNameLookup;

	static 
	{
		typeNameLookup = new HashMap<Integer, String>();
		typeNameLookup.put(31, "PACKAGE");
		typeNameLookup.put(19, "FUNCTION");
		typeNameLookup.put(83, "SHR");
		typeNameLookup.put(91, "LOR");
		typeNameLookup.put(26, "INTERNAL");
		typeNameLookup.put(139, "EXPONENT");
		typeNameLookup.put(67, "LT");
		typeNameLookup.put(77, "STAR");
		typeNameLookup.put(135, "BACKSLASH_SEQUENCE");
		typeNameLookup.put(47, "WHILE");
		typeNameLookup.put(79, "MOD");
		typeNameLookup.put(82, "SHL");
		typeNameLookup.put(9, "CONST");
		typeNameLookup.put(96, "MOD_ASSIGN");
		typeNameLookup.put(6, "CASE");
		typeNameLookup.put(29, "NEW");
		typeNameLookup.put(13, "DO");
		typeNameLookup.put(143, "IDENT_ASCII_START");
		typeNameLookup.put(88, "NOT");
		typeNameLookup.put(137, "HEX_NUMBER_LITERAL");
		typeNameLookup.put(-1, "EOF");
		typeNameLookup.put(95, "DIV_ASSIGN");
		typeNameLookup.put(5, "BREAK");
		typeNameLookup.put(55, "FINAL");
		typeNameLookup.put(62, "RPAREN");
		typeNameLookup.put(80, "INC");
		typeNameLookup.put(22, "IMPORT");
		typeNameLookup.put(124, "EOL");
		typeNameLookup.put(112, "XML_AT");
		typeNameLookup.put(53, "INCLUDE");
		typeNameLookup.put(38, "THIS");
		typeNameLookup.put(35, "RETURN");
		typeNameLookup.put(146, "XML_PI");
		typeNameLookup.put(145, "XML_CDATA");
		typeNameLookup.put(133, "REGULAR_EXPR_FIRST_CHAR");
		typeNameLookup.put(141, "IDENT_NAME_ASCII_START");
		typeNameLookup.put(50, "GET");
		typeNameLookup.put(45, "VAR");
		typeNameLookup.put(46, "VOID");
		typeNameLookup.put(36, "SUPER");
		typeNameLookup.put(49, "EACH");
		typeNameLookup.put(71, "EQ");
		typeNameLookup.put(84, "SHU");
		typeNameLookup.put(64, "RBRACK");
		typeNameLookup.put(97, "ADD_ASSIGN");
		typeNameLookup.put(32, "PRIVATE");
		typeNameLookup.put(57, "STATIC");
		typeNameLookup.put(89, "INV");
		typeNameLookup.put(37, "SWITCH");
		typeNameLookup.put(30, "NULL");
		typeNameLookup.put(102, "LAND_ASSIGN");
		typeNameLookup.put(14, "ELSE");
		typeNameLookup.put(118, "NUMBER");
		typeNameLookup.put(129, "DOUBLE_QUOTE_LITERAL");
		typeNameLookup.put(107, "ELLIPSIS");
		typeNameLookup.put(28, "NATIVE");
		typeNameLookup.put(125, "WHITESPACE");
		typeNameLookup.put(115, "UNDERSCORE");
		typeNameLookup.put(59, "LCURLY");
		typeNameLookup.put(12, "DELETE");
		typeNameLookup.put(42, "TRY");
		typeNameLookup.put(52, "NAMESPACE");
		typeNameLookup.put(134, "REGULAR_EXPR_CHAR");
		typeNameLookup.put(43, "TYPEOF");
		typeNameLookup.put(114, "XML_LS_END");
		typeNameLookup.put(92, "QUE");
		typeNameLookup.put(86, "OR");
		typeNameLookup.put(136, "IDENT_PART");
		typeNameLookup.put(68, "GT");
		typeNameLookup.put(44, "USE");
		typeNameLookup.put(138, "DEC_NUMBER");
		typeNameLookup.put(7, "CATCH");
		typeNameLookup.put(16, "FALSE");
		typeNameLookup.put(90, "LAND");
		typeNameLookup.put(110, "XML_E_TEND");
		typeNameLookup.put(39, "THROW");
		typeNameLookup.put(54, "DYNAMIC");
		typeNameLookup.put(127, "COMMENT_SINGLELINE");
		typeNameLookup.put(116, "DOLLAR");
		typeNameLookup.put(33, "PROTECTED");
		typeNameLookup.put(81, "DEC");
		typeNameLookup.put(8, "CLASS");
		typeNameLookup.put(63, "LBRACK");
		typeNameLookup.put(130, "REGULAR_EXPR_BODY");
		typeNameLookup.put(70, "GTE");
		typeNameLookup.put(18, "FOR");
		typeNameLookup.put(101, "SHU_ASSIGN");
		typeNameLookup.put(76, "SUB");
		typeNameLookup.put(85, "AND");
		typeNameLookup.put(104, "AND_ASSIGN");
		typeNameLookup.put(69, "LTE");
		typeNameLookup.put(113, "XML_LS_STD");
		typeNameLookup.put(61, "LPAREN");
		typeNameLookup.put(100, "SHR_ASSIGN");
		typeNameLookup.put(20, "IF");
		typeNameLookup.put(123, "ESCAPE_SEQUENCE");
		typeNameLookup.put(4, "AS");
		typeNameLookup.put(144, "XML_COMMENT");
		typeNameLookup.put(122, "UNICODE_ESCAPE");
		typeNameLookup.put(99, "SHL_ASSIGN");
		typeNameLookup.put(140, "DEC_NUMBER_LITERAL");
		typeNameLookup.put(23, "IN");
		typeNameLookup.put(21, "IMPLEMENTS");
		typeNameLookup.put(10, "CONTINUE");
		typeNameLookup.put(66, "COMMA");
		typeNameLookup.put(27, "IS");
		typeNameLookup.put(142, "IDENTIFIER");
		typeNameLookup.put(108, "XML_ELLIPSIS");
		typeNameLookup.put(105, "XOR_ASSIGN");
		typeNameLookup.put(75, "PLUS");
		typeNameLookup.put(65, "DOT");
		typeNameLookup.put(48, "WITH");
		typeNameLookup.put(87, "XOR");
		typeNameLookup.put(40, "TO");
		typeNameLookup.put(117, "ALPHABET");
		typeNameLookup.put(11, "DEFAULT");
		typeNameLookup.put(74, "NSAME");
		typeNameLookup.put(131, "REGULAR_EXPR_FLAG");
		typeNameLookup.put(119, "HEX_DIGIT");
		typeNameLookup.put(51, "SET");
		typeNameLookup.put(24, "INSTANCEOF");
		typeNameLookup.put(147, "XML_TEXT");
		typeNameLookup.put(41, "TRUE");
		typeNameLookup.put(58, "SEMI");
		typeNameLookup.put(73, "SAME");
		typeNameLookup.put(93, "COLON");
		typeNameLookup.put(106, "OR_ASSIGN");
		typeNameLookup.put(72, "NEQ");
		typeNameLookup.put(128, "SINGLE_QUOTE_LITERAL");
		typeNameLookup.put(17, "FINALLY");
		typeNameLookup.put(56, "OVERRIDE");
		typeNameLookup.put(111, "XML_NS_OP");
		typeNameLookup.put(60, "RCURLY");
		typeNameLookup.put(94, "ASSIGN");
		typeNameLookup.put(132, "REGULAR_EXPR_LITERAL");
		typeNameLookup.put(25, "INTERFACE");
		typeNameLookup.put(109, "XML_TEND");
		typeNameLookup.put(78, "DIV");
		typeNameLookup.put(120, "CR");
		typeNameLookup.put(34, "PUBLIC");
		typeNameLookup.put(15, "EXTENDS");
		typeNameLookup.put(98, "SUB_ASSIGN");
		typeNameLookup.put(126, "COMMENT_MULTILINE");
		typeNameLookup.put(103, "LOR_ASSIGN");
		typeNameLookup.put(121, "LF");
	}
	
	public static String getTypeName(Tree element)
	{
		return getTypeName(element.getType());
	}
	
	public static String getTypeName(int type) 
	{
		String name = typeNameLookup.get(type);
		return name == null ? "Unknown: " + type : name;
	}
	
	private static boolean isElementOfType(Tree element, int type) 
	{
		return element.getType() == type;
	}

	public static boolean isIdentifier(Tree element)
	{
		return isElementOfType(element, AS3_exParser.IDENTIFIER);
	}

	public static boolean isVoid(Tree element)
	{
		return isElementOfType(element, AS3_exParser.VOID);
	}
	
	public static boolean isDot(Tree element) 
	{
		return isElementOfType(element, AS3_exParser.DOT);
	}
	
	public static boolean isComma(Tree element) 
	{
		return isElementOfType(element, AS3_exParser.COMMA);
	}

	public static boolean isCurlyOpen(Tree element) 
	{
		return isElementOfType(element, AS3_exParser.LCURLY);
	}
	
	public static boolean isCurlyClosed(Tree element) 
	{
		return isElementOfType(element, AS3_exParser.RCURLY);
	}
	
	public static boolean isParenthesisOpen(Tree element) 
	{
		return isElementOfType(element, AS3_exParser.LPAREN);
	}
	
	public static boolean isParenthesisClosed(Tree element) 
	{
		return isElementOfType(element, AS3_exParser.RPAREN);
	}
	
	public static boolean isSemicolon(Tree element) 
	{
		return isElementOfType(element, AS3_exParser.SEMI);
	}
	
	public static boolean isColon(Tree element) 
	{
		return isElementOfType(element, AS3_exParser.COLON);
	}

	public static boolean isExtends(Tree element) 
	{
		return isElementOfType(element, AS3_exParser.EXTENDS);
	}
	
	public static boolean isImplements(Tree element) 
	{
		return isElementOfType(element, AS3_exParser.IMPLEMENTS);
	}
}

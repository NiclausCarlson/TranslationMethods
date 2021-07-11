package generated;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.*;
import java.util.stream.Collectors;


public class __Parser {
public __Lexer lexer;
public String EPS;
public Map<String, Set<String>> first;
public Map<String, Set<String>> follow;

public __Parser(__Lexer lexer) {
try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("secondHW/generated/first.data"))) {this.first = (Map<String, Set<String>>)ois.readObject();
}catch (Exception ex) {
System.out.println(ex.getMessage());
}try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("secondHW/generated/follow.data"))) {this.follow = (Map<String, Set<String>>)ois.readObject();
}catch (Exception ex) {
System.out.println(ex.getMessage());
}this.lexer = lexer;
this.EPS = "EPS";
}

public void consume(String token) {
if(!token.equals(lexer.curToken)) {
throw new RuntimeException("Expected " + lexer.curToken + " but found " + token);}
lexer.setNextToken();
}
public boolean checkFirstWithoutEps(String str, String rule) {
return first.get(rule).contains(str) && !str.equals(EPS);
}
public boolean hasNext(String name) {
switch(name) {
case "argument":
if (EPS.equals("NAMELDOTStype") || first.get("NAMELDOTStype").contains(EPS)) return true;
return false;
case "function":
if (EPS.equals("FUNbody") || first.get("FUNbody").contains(EPS)) return true;
return false;
case "arguments1":
if (EPS.equals("OBarguments2CB") || first.get("OBarguments2CB").contains(EPS)) return true;
return false;
case "body":
if (EPS.equals("NAMEarguments1returnType") || first.get("NAMEarguments1returnType").contains(EPS)) return true;
return false;
case "type":
if (EPS.equals("SIMPLE_TYPE") || first.get("SIMPLE_TYPE").contains(EPS)) return true;
return false;
case "arguments2":
if (EPS.equals("argumentarguments3") || first.get("argumentarguments3").contains(EPS)) return true;
if (EPS.equals("EPS") || first.get("EPS").contains(EPS)) return true;
return false;
case "returnType":
if (EPS.equals("LDOTStype") || first.get("LDOTStype").contains(EPS)) return true;
if (EPS.equals("EPS") || first.get("EPS").contains(EPS)) return true;
return false;
case "arguments3":
if (EPS.equals("COMMAargumentarguments3") || first.get("COMMAargumentarguments3").contains(EPS)) return true;
if (EPS.equals("EPS") || first.get("EPS").contains(EPS)) return true;
return false;
default: throw new RuntimeException("Unknown token");
}

}
public __Node argument() {
String curToken = lexer.curToken;
String curStr = lexer.curStr;
__Node res = new __Node("argument");
boolean hasEps = hasNext("argument");
if (checkFirstWithoutEps(curToken, "NAMELDOTStype")) {
 consume("NAME");res.addChild(new __Node("NAME", curStr));curStr = lexer.curStr;consume("LDOTS");res.addChild(new __Node("LDOTS", curStr));curStr = lexer.curStr;res.addChild(type());curStr = lexer.curStr;
}else if(hasEps && follow.get("argument").contains(curToken)) {
res.addChild(new __Node(EPS, EPS));

}else {
throw new RuntimeException("Unexpected token " + curToken);
}
return res;
}
public __Node function() {
String curToken = lexer.curToken;
String curStr = lexer.curStr;
__Node res = new __Node("function");
boolean hasEps = hasNext("function");
if (checkFirstWithoutEps(curToken, "FUNbody")) {
 consume("FUN");res.addChild(new __Node("FUN", curStr));curStr = lexer.curStr;res.addChild(body());curStr = lexer.curStr;
}else if(hasEps && follow.get("function").contains(curToken)) {
res.addChild(new __Node(EPS, EPS));

}else {
throw new RuntimeException("Unexpected token " + curToken);
}
return res;
}
public __Node arguments1() {
String curToken = lexer.curToken;
String curStr = lexer.curStr;
__Node res = new __Node("arguments1");
boolean hasEps = hasNext("arguments1");
if (checkFirstWithoutEps(curToken, "OBarguments2CB")) {
 consume("OB");res.addChild(new __Node("OB", curStr));curStr = lexer.curStr;res.addChild(arguments2());curStr = lexer.curStr;consume("CB");res.addChild(new __Node("CB", curStr));curStr = lexer.curStr;
}else if(hasEps && follow.get("arguments1").contains(curToken)) {
res.addChild(new __Node(EPS, EPS));

}else {
throw new RuntimeException("Unexpected token " + curToken);
}
return res;
}
public __Node body() {
String curToken = lexer.curToken;
String curStr = lexer.curStr;
__Node res = new __Node("body");
boolean hasEps = hasNext("body");
if (checkFirstWithoutEps(curToken, "NAMEarguments1returnType")) {
 consume("NAME");res.addChild(new __Node("NAME", curStr));curStr = lexer.curStr;res.addChild(arguments1());curStr = lexer.curStr;res.addChild(returnType());curStr = lexer.curStr;
}else if(hasEps && follow.get("body").contains(curToken)) {
res.addChild(new __Node(EPS, EPS));

}else {
throw new RuntimeException("Unexpected token " + curToken);
}
return res;
}
public __Node type() {
String curToken = lexer.curToken;
String curStr = lexer.curStr;
__Node res = new __Node("type");
boolean hasEps = hasNext("type");
if (checkFirstWithoutEps(curToken, "SIMPLE_TYPE")) {
 consume("SIMPLE_TYPE");res.addChild(new __Node("SIMPLE_TYPE", curStr));curStr = lexer.curStr;
}else if(hasEps && follow.get("type").contains(curToken)) {
res.addChild(new __Node(EPS, EPS));

}else {
throw new RuntimeException("Unexpected token " + curToken);
}
return res;
}
public __Node arguments2() {
String curToken = lexer.curToken;
String curStr = lexer.curStr;
__Node res = new __Node("arguments2");
boolean hasEps = hasNext("arguments2");
if (checkFirstWithoutEps(curToken, "argumentarguments3")) {
 res.addChild(argument());curStr = lexer.curStr;res.addChild(arguments3());curStr = lexer.curStr;
}else if(hasEps && follow.get("arguments2").contains(curToken)) {
res.addChild(new __Node(EPS, EPS));

}else {
throw new RuntimeException("Unexpected token " + curToken);
}
return res;
}
public __Node returnType() {
String curToken = lexer.curToken;
String curStr = lexer.curStr;
__Node res = new __Node("returnType");
boolean hasEps = hasNext("returnType");
if (checkFirstWithoutEps(curToken, "LDOTStype")) {
 consume("LDOTS");res.addChild(new __Node("LDOTS", curStr));curStr = lexer.curStr;res.addChild(type());curStr = lexer.curStr;
}else if(hasEps && follow.get("returnType").contains(curToken)) {
res.addChild(new __Node(EPS, EPS));

}else {
throw new RuntimeException("Unexpected token " + curToken);
}
return res;
}
public __Node arguments3() {
String curToken = lexer.curToken;
String curStr = lexer.curStr;
__Node res = new __Node("arguments3");
boolean hasEps = hasNext("arguments3");
if (checkFirstWithoutEps(curToken, "COMMAargumentarguments3")) {
 consume("COMMA");res.addChild(new __Node("COMMA", curStr));curStr = lexer.curStr;res.addChild(argument());curStr = lexer.curStr;res.addChild(arguments3());curStr = lexer.curStr;
}else if(hasEps && follow.get("arguments3").contains(curToken)) {
res.addChild(new __Node(EPS, EPS));

}else {
throw new RuntimeException("Unexpected token " + curToken);
}
return res;
}
}
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
try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("calc/generated/first.data"))) {this.first = (Map<String, Set<String>>)ois.readObject();
}catch (Exception ex) {
System.out.println(ex.getMessage());
}try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("calc/generated/follow.data"))) {this.follow = (Map<String, Set<String>>)ois.readObject();
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
case "number":
if (EPS.equals("integralfractional") || first.get("integralfractional").contains(EPS)) return true;
return false;
case "e'":
if (EPS.equals("PLUSte'") || first.get("PLUSte'").contains(EPS)) return true;
if (EPS.equals("EPS") || first.get("EPS").contains(EPS)) return true;
if (EPS.equals("MINUSte'") || first.get("MINUSte'").contains(EPS)) return true;
if (EPS.equals("EPS") || first.get("EPS").contains(EPS)) return true;
return false;
case "t'":
if (EPS.equals("MULft'") || first.get("MULft'").contains(EPS)) return true;
if (EPS.equals("EPS") || first.get("EPS").contains(EPS)) return true;
if (EPS.equals("DIVft'") || first.get("DIVft'").contains(EPS)) return true;
if (EPS.equals("EPS") || first.get("EPS").contains(EPS)) return true;
if (EPS.equals("POWt") || first.get("POWt").contains(EPS)) return true;
return false;
case "t":
if (EPS.equals("ft'") || first.get("ft'").contains(EPS)) return true;
return false;
case "e":
if (EPS.equals("te'") || first.get("te'").contains(EPS)) return true;
return false;
case "f":
if (EPS.equals("number") || first.get("number").contains(EPS)) return true;
if (EPS.equals("OPEN_BRACKETeCLOSE_BRACKET") || first.get("OPEN_BRACKETeCLOSE_BRACKET").contains(EPS)) return true;
return false;
case "integral":
if (EPS.equals("NUMBER") || first.get("NUMBER").contains(EPS)) return true;
return false;
case "fractional":
if (EPS.equals("DOTNUMBER") || first.get("DOTNUMBER").contains(EPS)) return true;
if (EPS.equals("EPS") || first.get("EPS").contains(EPS)) return true;
return false;
default: throw new RuntimeException("Unknown token");
}

}
public __Node number() {
String curToken = lexer.curToken;
String curStr = lexer.curStr;
__Node res = new __Node("number");
boolean hasEps = hasNext("number");
if (checkFirstWithoutEps(curToken, "integralfractional")) {
 res.addChild(integral());curStr = lexer.curStr;res.addChild(fractional());curStr = lexer.curStr;
      res.number = Double.parseDouble(res.children.get(1-1).integral + res.children.get(2-1).fractional);
    
}else if(hasEps && follow.get("number").contains(curToken)) {
res.addChild(new __Node(EPS, EPS));

}else {
throw new RuntimeException("Unexpected token " + curToken);
}
return res;
}
public __Node eQuote(double i) {
String curToken = lexer.curToken;
String curStr = lexer.curStr;
__Node res = new __Node("e'");
boolean hasEps = hasNext("e'");
if (checkFirstWithoutEps(curToken, "PLUSte'")) {
 consume("PLUS");res.addChild(new __Node("PLUS", curStr));curStr = lexer.curStr;res.addChild(t());curStr = lexer.curStr;res.addChild(eQuote(i + res.children.get(2-1).tVal));curStr = lexer.curStr;res.sumVal = res.children.get(3-1).sumVal;
}else if (checkFirstWithoutEps(curToken, "MINUSte'")) {
consume("MINUS");res.addChild(new __Node("MINUS", curStr));curStr = lexer.curStr;res.addChild(t());curStr = lexer.curStr;res.addChild(eQuote(i - res.children.get(2-1).tVal));curStr = lexer.curStr;res.sumVal = res.children.get(3-1).sumVal;
}else if(hasEps && follow.get("e'").contains(curToken)) {
res.addChild(new __Node(EPS, EPS));
res.sumVal = i;
}else {
throw new RuntimeException("Unexpected token " + curToken);
}
return res;
}
public __Node tQuote(double i) {
String curToken = lexer.curToken;
String curStr = lexer.curStr;
__Node res = new __Node("t'");
boolean hasEps = hasNext("t'");
if (checkFirstWithoutEps(curToken, "MULft'")) {
 consume("MUL");res.addChild(new __Node("MUL", curStr));curStr = lexer.curStr;res.addChild(f());curStr = lexer.curStr;res.addChild(tQuote(i * res.children.get(2-1).fVal));curStr = lexer.curStr;res.mulVal = res.children.get(3-1).mulVal;
}else if (checkFirstWithoutEps(curToken, "DIVft'")) {
consume("DIV");res.addChild(new __Node("DIV", curStr));curStr = lexer.curStr;res.addChild(f());curStr = lexer.curStr;res.addChild(tQuote(i / res.children.get(2-1).fVal));curStr = lexer.curStr;res.mulVal = res.children.get(3-1).mulVal;
}else if (checkFirstWithoutEps(curToken, "POWt")) {
consume("POW");res.addChild(new __Node("POW", curStr));curStr = lexer.curStr;res.addChild(t());curStr = lexer.curStr;res.mulVal = Math.pow(i, res.children.get(2-1).tVal);
}else if(hasEps && follow.get("t'").contains(curToken)) {
res.addChild(new __Node(EPS, EPS));
res.mulVal = i;
}else {
throw new RuntimeException("Unexpected token " + curToken);
}
return res;
}
public __Node t() {
String curToken = lexer.curToken;
String curStr = lexer.curStr;
__Node res = new __Node("t");
boolean hasEps = hasNext("t");
if (checkFirstWithoutEps(curToken, "ft'")) {
 res.addChild(f());curStr = lexer.curStr;res.addChild(tQuote(res.children.get(1-1).fVal));curStr = lexer.curStr; res.tVal = res.children.get(2-1).mulVal; 
}else if(hasEps && follow.get("t").contains(curToken)) {
res.addChild(new __Node(EPS, EPS));

}else {
throw new RuntimeException("Unexpected token " + curToken);
}
return res;
}
public __Node e() {
String curToken = lexer.curToken;
String curStr = lexer.curStr;
__Node res = new __Node("e");
boolean hasEps = hasNext("e");
if (checkFirstWithoutEps(curToken, "te'")) {
 res.addChild(t());curStr = lexer.curStr;res.addChild(eQuote(res.children.get(1-1).tVal));curStr = lexer.curStr; res.eVal = res.children.get(2-1).sumVal; 
}else if(hasEps && follow.get("e").contains(curToken)) {
res.addChild(new __Node(EPS, EPS));

}else {
throw new RuntimeException("Unexpected token " + curToken);
}
return res;
}
public __Node f() {
String curToken = lexer.curToken;
String curStr = lexer.curStr;
__Node res = new __Node("f");
boolean hasEps = hasNext("f");
if (checkFirstWithoutEps(curToken, "number")) {
 res.addChild(number());curStr = lexer.curStr;res.fVal = res.children.get(1-1).number;
}else if (checkFirstWithoutEps(curToken, "OPEN_BRACKETeCLOSE_BRACKET")) {
consume("OPEN_BRACKET");res.addChild(new __Node("OPEN_BRACKET", curStr));curStr = lexer.curStr;res.addChild(e());curStr = lexer.curStr;res.fVal = res.children.get(2-1).eVal;consume("CLOSE_BRACKET");res.addChild(new __Node("CLOSE_BRACKET", curStr));curStr = lexer.curStr;
}else if(hasEps && follow.get("f").contains(curToken)) {
res.addChild(new __Node(EPS, EPS));

}else {
throw new RuntimeException("Unexpected token " + curToken);
}
return res;
}
public __Node integral() {
String curToken = lexer.curToken;
String curStr = lexer.curStr;
__Node res = new __Node("integral");
boolean hasEps = hasNext("integral");
if (checkFirstWithoutEps(curToken, "NUMBER")) {
 consume("NUMBER");res.addChild(new __Node("NUMBER", curStr));curStr = lexer.curStr;res.integral = res.children.get(1-1).value;
}else if(hasEps && follow.get("integral").contains(curToken)) {
res.addChild(new __Node(EPS, EPS));

}else {
throw new RuntimeException("Unexpected token " + curToken);
}
return res;
}
public __Node fractional() {
String curToken = lexer.curToken;
String curStr = lexer.curStr;
__Node res = new __Node("fractional");
boolean hasEps = hasNext("fractional");
if (checkFirstWithoutEps(curToken, "DOTNUMBER")) {
 consume("DOT");res.addChild(new __Node("DOT", curStr));curStr = lexer.curStr;consume("NUMBER");res.addChild(new __Node("NUMBER", curStr));curStr = lexer.curStr;res.fractional = res.children.get(1-1).value + res.children.get(2-1).value;
}else if(hasEps && follow.get("fractional").contains(curToken)) {
res.addChild(new __Node(EPS, EPS));
res.fractional = "";
}else {
throw new RuntimeException("Unexpected token " + curToken);
}
return res;
}
}
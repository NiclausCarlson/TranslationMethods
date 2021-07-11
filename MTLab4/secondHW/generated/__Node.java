package generated;

import java.util.List;
import java.util.ArrayList;


public class __Node {
public List<__Node> children;
public String _name;
public String value;

public __Node() {
this("", "");
}
public __Node(String name) {
this(name, "");
}
public __Node(String name, String value) {
this.children = new ArrayList<>();
this._name = name;
this.value = value;
}

public void addChild(__Node child) {
	this.children.add(child);
}
}
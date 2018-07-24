##1.EL表达式的简介

EL表达式是一种JSP技术，能够代替JSP中原本要用Java语言进行显示的语句，使得代码更容易编写与维护。最基本的语法是${express}。

##2.获取并显示数据

1)从四个域中通过key找到简单数据并显示出来。表达式代码：

${name}      <!-- 类比于<%=pageContext.findAttribute("name") %> -->
四个域的寻找顺序是 page，request，session，application。用EL表达式还有个好处，若找不到键值为name的属性值，不会显示null，会显示空字符串。若是确定键值是在request域中，则可以用如下EL表达式代码：
${requestScope.name}
2)从存储在WEB域中的封装了数据的JavaBean中得到对象的某个属性值并显示出来。
<%
	Person p = new Person();
	Address address = new Address();
	address.setCity("浙江");//Adress类中有个私有String属性为city
	p.setName("mike");//Person类中有私有String属性为name
	p.setAddress(address);//Person类中有私有Adress属性为address
	session.setAttribute("person", p);//存入sessionScope
%>
${person.name}<!-- 从WEB域中找到键值为person的对象，然后再person对象中找到name属性 -->
${person.address.city}
${person['name']}<!-- 也可以用[]方式 -->
${person['address']['city']}
除了用.方式获得对象的属性，也可以用[ ]方式，当然遇到键值名字中有-的，如mike-abc，或者key值为数字开头，则只能用[ ]方式。
3)从List集合对象中获取某个值并显示。


<%
	List<Person> list = new ArrayList<Person>();
	list.add(new Person("kkk"));
	list.add(new Person("sss"));
	list.add(new Person("jjj"));
	application.setAttribute("list_1", list);
%>
${list_1[1].name }

4)从Map中获取某个值并显示。

<%
	Map map = new HashMap();
	map.put("a", new Person("aaa"));
	map.put("b", new Person("bbb"));
	map.put("1", new Person("ccc"));
	request.setAttribute("map", map);
%>
${map['1'].name }<!-- 是数字的话只能用括号，就算put进去的key值是字符串类型-->
${map.a.name }

##3.执行运算
1)语法：${运算表达式}

2)常见运算符：==(eq)   !=(ne)    <(lt)    >(gt)    <=(le)    >=(ge)    &&(and)   ||(or)   !(not)

3)判断是否为空：${empty name }

4)三目运算符：${name == null?"null":name }



##4.获取常用对象

* 1)语法：${隐式对象名称}

* 2)隐式对象表

    >对象名	  用法 	 等价JSP代码或作用
```$xslt
param	${param.name}	request.getParameter(name)
paramValues	${paramValues.name}	request.getParameterValues(name)//返回一个字符串数组
header	${header.name}	request.getHeader(name)
headerValues	${headerValues.name}	request.getHeaderValues(name)
cookie	${cookie.name.value}	request.getCookie()
initParam	${initParam.name}	ServletContext.getInitparameter(name)
pageContext	${pageContext.request.contextPath}	获取WEB应用名
pageContext	${pageContext.request.sessionid}	获取sessionId
pageContext	${pageContext.request.remoteAddr}	获取主机名
```


##5.EL表达式原理及注意点

> 原理：EL表达式在获取某个对象的属性值时，先将某个属性值首字母变成大写，然后加上get前缀，拼接成getter方法，通过反射将该对象构建出来，然后再对该对象执行getter方法，这与私有属性并没有关系，所以要注意，JavaBean的属性名要小写，且要有getter方法，不然会报错。
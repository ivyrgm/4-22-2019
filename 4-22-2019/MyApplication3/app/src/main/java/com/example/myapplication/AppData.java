package com.example.myapplication;




public class AppData {
    private String data;

    public AppData()
    {
        this.data = "";
        this.name = "";
        this.age = "";
    }

    public String getData(){
        return data;
    }

    public void setData(String _id, String name, String age){
        StringBuffer tempData = new StringBuffer(this.data);
        tempData.append("ID:Name:Age = " + _id + ":" + name +
                ":" + age + "\n");

        this.data = tempData.toString();
    }

    private String name;
    private String age;

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAge(String age)
    {
        this.age = age;
    }

    public String getName()
    {
        return name;
    }

    public String getAge()
    {
        return age;
    }



}

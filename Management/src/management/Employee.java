package management;

public class Employee {
    
    private String name;
    private String lastName;
    private int age;
    private boolean gender; //true: female  false: male
    
    public void setAge(int age){
        if(age > 18)
           this.age = age;
        else
            this.age = 18;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public boolean isMale() {
        return gender;
    }
    
    
    
    public Employee(String name, String lastName, int age, boolean gender) {
        this.name = name;
        this.lastName = lastName;
        setAge(age);
        this.gender = gender;
    }
    
    
}

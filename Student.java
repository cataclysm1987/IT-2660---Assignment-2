public class Student implements Comparable<Student> {
    public int Id;
    public String Name;
    public String Major;

    @Override
    public int compareTo(Student o) {
        int last = this.Name.compareTo(o.Name);
        return last;
    }
}
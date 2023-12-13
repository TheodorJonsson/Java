import java.util.List;

public interface Builder {
    Builder name(String name);
    Builder type(String type);
    Builder age(int age);
    Builder addtoy(String toy);
    Object build();
}

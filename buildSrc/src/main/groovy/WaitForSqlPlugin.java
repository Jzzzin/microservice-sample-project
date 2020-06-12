import org.gradle.api.Plugin;
import org.gradle.api.Project;

// DB Connection Plugin Create
public class WaitForSqlPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getTasks().create("waitForSql", WaitForSql.class);
    }
}

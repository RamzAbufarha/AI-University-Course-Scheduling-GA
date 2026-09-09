package ga;

import model.Chromosome;
import model.CourseGroup;

public interface Selection {

    Chromosome select(
            Population population,
            CourseGroup[] courseGroups,
            String[] preferredMorningInstructors);
}
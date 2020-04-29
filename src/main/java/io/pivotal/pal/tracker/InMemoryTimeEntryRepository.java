package io.pivotal.pal.tracker;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    HashMap<Long, TimeEntry> timeEntries = new HashMap<>();
    private long id = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry savedEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );
        timeEntries.put(id, savedEntry);
        id++;
        return savedEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return timeEntries.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntries.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (timeEntries.get(id) == null) {
            return null;
        }
        TimeEntry updatedTimeEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );
        timeEntries.put(id, updatedTimeEntry);
        return updatedTimeEntry;
    }

    @Override
    public void delete(long id) {
        timeEntries.remove(id);
    }
}

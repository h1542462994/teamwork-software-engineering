package org.learning.server.service.impl;

import org.learning.server.entity.Course;
import org.learning.server.entity.CourseNode;
import org.learning.server.repository.CourseNodeRepository;
import org.learning.server.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements org.learning.server.service.CourseService {
    /*
    private CourseNodeRepository courseNodeRepository;
    private SectionNodeRepository sectionNodeRepository;
    private MediaNodeRepository mediaNodeRepository;

    @Autowired
    public void setCourseNodeRepository(CourseNodeRepository courseNodeRepository) {
        this.courseNodeRepository = courseNodeRepository;
    }

    @Autowired
    public void setSectionNodeRepository(SectionNodeRepository sectionNodeRepository) {
        this.sectionNodeRepository = sectionNodeRepository;
    }

    @Autowired
    public void setMediaNodeRepository(MediaNodeRepository mediaNodeRepository) {
        this.mediaNodeRepository = mediaNodeRepository;
    }

    private List<MediaNode> getNeedToDeleteMedias(Section instance) {
        List<MediaNode> savedInstances = mediaNodeRepository.findAllBySectionNodeId(instance.getId());
        Set<Integer> current = instance.getMedias().stream()
                .map(Media::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        return savedInstances
                .stream().filter((mediaNode -> !current.contains(mediaNode.getId()))).collect(Collectors.toList());
    }

    private void saveOrUpdateMedias(Section instance){
        int index = 0;
        List<MediaNode> needToDeleteMedias = getNeedToDeleteMedias(instance);
        for (Media media: instance.getMedias()) {
            MediaNode mediaNode = new MediaNode();
            mediaNode.setId(mediaNode.getId());
            mediaNode.setMediaType(media.getMediaType());
            mediaNode.setData(media.getData());
            mediaNode.setIndex(index++);
            mediaNode.setSectionNodeId(instance.getId());
            mediaNodeRepository.save(mediaNode);
            media.setId(mediaNode.getId());
        }
        mediaNodeRepository.deleteAll(needToDeleteMedias);
    }

    private List<SectionNode> getNeedToDeleteSections(org.learning.server.model.Course instance){
        List<SectionNode> savedInstances = sectionNodeRepository.findAllByCourseNodeId(instance.getId());
        Set<Integer> current = instance.getSections().stream()
                .map(Section::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        return savedInstances
                .stream().filter((sectionNode -> !current.contains(sectionNode.getId()))).collect(Collectors.toList());
    }

    private void saveOrUpdateSections(org.learning.server.model.Course instance) {
        int index = 0;
        List<SectionNode> needToDeleteSections = getNeedToDeleteSections(instance);
        for (Section section: instance.getSections()) {
            SectionNode sectionNode = new SectionNode();
            sectionNode.setId(section.getId());
            sectionNode.setIndex(index++);
            sectionNode.setName(section.getName());
            sectionNode.setDescription(section.getDescription());
            sectionNode.setCourseNodeId(instance.getId());
            sectionNodeRepository.save(sectionNode);
        }
        sectionNodeRepository.deleteAll(needToDeleteSections);
    }

    @Override
    public void saveOrUpdate(Course instance){
        Course course = new Course();
        course.setId(instance.getId());
        course.setName(instance.getName());
        course.setInfo(instance.getInfo());
        course.setStartTime(instance.getStartTime());
        course.setEndTime(instance.getEndTime());
        courseNodeRepository.save(course);
        instance.setId(course.getId());

        saveOrUpdateSections(instance);
    }
*/
    @Autowired
    private CourseRepository course;
    @Autowired
    private CourseNodeRepository coursenode;

    @Override
    public void saveOrUpdate(Course instance) {
    }

    @Override
    public List<Course> getCourses() {

        return course.findAll();
    }

    @Override
    public Optional<Course> getCourse(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Course instance){
        return true;
    }

    @Override
    public List<Course> findCoursesByName(String name) {

        return course.findAllByName(name);
    }

    @Override
    public List<CourseNode> findCnodeById(Integer id) {

        return coursenode.findAllByCourseid(id);
    }
}

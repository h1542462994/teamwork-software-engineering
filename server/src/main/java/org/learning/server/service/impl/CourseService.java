package org.learning.server.service.impl;

import org.learning.server.entity.CourseNode;
import org.learning.server.entity.MediaNode;
import org.learning.server.entity.SectionNode;
import org.learning.server.model.Course;
import org.learning.server.model.Media;
import org.learning.server.model.Section;
import org.learning.server.repository.CourseNodeRepository;
import org.learning.server.repository.MediaNodeRepository;
import org.learning.server.repository.SectionNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseService implements org.learning.server.service.CourseService {
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

    private List<SectionNode> getNeedToDeleteSections(Course instance){
        List<SectionNode> savedInstances = sectionNodeRepository.findAllByCourseNodeId(instance.getId());
        Set<Integer> current = instance.getSections().stream()
                .map(Section::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        return savedInstances
                .stream().filter((sectionNode -> !current.contains(sectionNode.getId()))).collect(Collectors.toList());
    }

    private void saveOrUpdateSections(Course instance) {
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
        CourseNode courseNode = new CourseNode();
        courseNode.setId(instance.getId());
        courseNode.setName(instance.getName());
        courseNode.setInfo(instance.getInfo());
        courseNode.setStartTime(instance.getStartTime());
        courseNode.setEndTime(instance.getEndTime());
        courseNodeRepository.save(courseNode);
        instance.setId(courseNode.getId());

        saveOrUpdateSections(instance);
    }

    @Override
    public List<Course> getCourses() {
        return null;
    }

    @Override
    public Optional<Course> getCourse(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Course instance){
        return true;
    }
}

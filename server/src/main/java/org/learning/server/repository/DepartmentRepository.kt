package org.learning.server.repository

import org.learning.server.entity.Department
import org.springframework.data.repository.CrudRepository

interface DepartmentRepository: CrudRepository<Department, Int> {
}
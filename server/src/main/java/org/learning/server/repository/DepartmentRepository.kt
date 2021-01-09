package org.learning.server.repository

import org.learning.server.entity.Department
import org.springframework.data.repository.CrudRepository

@Deprecated("")
interface DepartmentRepository: CrudRepository<Department, Int> {
}
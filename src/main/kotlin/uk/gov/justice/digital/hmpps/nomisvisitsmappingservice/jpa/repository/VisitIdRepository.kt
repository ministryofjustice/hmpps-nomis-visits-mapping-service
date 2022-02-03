package uk.gov.justice.digital.hmpps.nomisvisitsmappingservice.jpa.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import uk.gov.justice.digital.hmpps.nomisvisitsmappingservice.jpa.VisitId
import java.util.Optional

@Repository
interface VisitIdRepository : CrudRepository<VisitId, Long> {
  fun findOneByVsipId(vsipId: String): Optional<VisitId>
}

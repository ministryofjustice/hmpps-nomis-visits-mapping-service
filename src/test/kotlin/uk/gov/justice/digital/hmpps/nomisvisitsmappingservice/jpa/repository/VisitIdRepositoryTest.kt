package uk.gov.justice.digital.hmpps.nomisvisitsmappingservice.jpa.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.context.annotation.Import
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ActiveProfiles
import uk.gov.justice.digital.hmpps.nomisvisitsmappingservice.config.AuthenticationFacade
import uk.gov.justice.digital.hmpps.nomisvisitsmappingservice.helper.builders.Repository
import uk.gov.justice.digital.hmpps.nomisvisitsmappingservice.jpa.VisitId
import uk.gov.justice.digital.hmpps.nomisvisitsmappingservice.jpa.VisitIdSource

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(AuthenticationFacade::class, Repository::class)
@WithMockUser
class VisitIdRepositoryTest {
  @Autowired
  lateinit var builderRepository: Repository

  @Autowired
  lateinit var repository: VisitIdRepository

  @Autowired
  lateinit var entityManager: TestEntityManager

  @Test
  fun saveVisitId() {

    repository.save(VisitId(123, "123", "TIMESTAMP", VisitIdSource.MIGRATION))
    entityManager.flush()

    val persistedVisitId = repository.findById(123L).orElseThrow()
    with(persistedVisitId) {
      assertThat(nomisId).isEqualTo(123)
      assertThat(vsipId).isEqualTo("123")
      assertThat(label).isEqualTo("TIMESTAMP")
      assertThat(source).isEqualTo(VisitIdSource.MIGRATION)
    }
  }
}

import com.primo.data.dataModule
import com.primo.database.databaseModule
import com.primo.domain.domainModule
import com.primo.feature.featureModule
import org.koin.dsl.module

val appModule = module {
    includes(featureModule, domainModule, dataModule, databaseModule)
}

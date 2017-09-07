import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import com.example.android.architecture.blueprints.todoapp.data.source.local.TasksLocalDataSource
import com.example.android.architecture.blueprints.todoapp.data.source.remote.TasksRemoteDataSource
import org.koin.android.AndroidModule

class RepositoryModule : AndroidModule() {
    override fun context() = declareContext {
        //TODO bind multi components for TasksDataSource
        provide { TasksRepository(TasksRemoteDataSource(), TasksLocalDataSource(applicationContext)) }
    }
}
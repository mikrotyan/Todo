import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todolist.domain.models.TodoDomain

class AddTodoViewModel(application: Application) : AndroidViewModel(application) {

    private val _todo = MutableLiveData<TodoDomain?>()
    val todo: MutableLiveData<TodoDomain?>
        get() = _todo

    fun setTodoData(todo: TodoDomain) {
        _todo.value = todo
    }

    fun clearTodoData() {
        _todo.value = null
    }
}

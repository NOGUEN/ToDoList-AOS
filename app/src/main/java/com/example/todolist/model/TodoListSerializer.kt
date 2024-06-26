import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.example.todolist.todo.TodoProto
import java.io.InputStream
import java.io.OutputStream

object TodoListSerializer : Serializer<TodoProto.TodoList> {
    override val defaultValue: TodoProto.TodoList = TodoProto.TodoList.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): TodoProto.TodoList {
        try {
            return TodoProto.TodoList.parseFrom(input)
        } catch (e: Exception) {
            throw CorruptionException("Cannot read proto.", e)
        }
    }

    override suspend fun writeTo(t: TodoProto.TodoList, output: OutputStream) = t.writeTo(output)
}

val Context.todoDataStore: DataStore<TodoProto.TodoList> by dataStore(
    fileName = "todos.pb",
    serializer = TodoListSerializer
)
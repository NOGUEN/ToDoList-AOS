import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.example.todolist.todo.TodoProto
import java.io.InputStream
import java.io.OutputStream

object ToDoSerializer : Serializer<TodoProto.Todo> {
    override val defaultValue: TodoProto.Todo = TodoProto.Todo.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): TodoProto.Todo {
        return TodoProto.Todo.parseFrom(input)
    }

    override suspend fun writeTo(t: TodoProto.Todo, output: OutputStream) {
        t.writeTo(output)
    }
}



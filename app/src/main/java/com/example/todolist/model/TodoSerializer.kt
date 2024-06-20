import androidx.datastore.core.Serializer
import com.example.todolist.TodoProto
import java.io.InputStream
import java.io.OutputStream

object ToDoSerializer : Serializer<TodoProto.ToDo> {
    override val defaultValue: TodoProto.ToDo = TodoProto.ToDo.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): TodoProto.ToDo {
        return TodoProto.ToDo.parseFrom(input)
    }

    override suspend fun writeTo(t: TodoProto.ToDo, output: OutputStream) {
        t.writeTo(output)
    }
}

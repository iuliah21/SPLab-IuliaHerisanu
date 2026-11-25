package ro.uvt.commands;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.Map;
import java.util.concurrent.*;

@Component
public class AsyncCommandExecutor implements CommandExecutor {
    private final ExecutorService executor =
            Executors.newFixedThreadPool(4);

    private final Map<String, CompletableFuture<?>> tasks =
            new ConcurrentHashMap<>();

    @Override
    public <T> CompletableFuture<T> execute(Command<T> command) {
        return CompletableFuture.supplyAsync(
                command::execute, executor);
    }

    public <T> String submit(Command<T> command) {
        String id = UUID.randomUUID().toString();

        tasks.put(id, execute(command));

        return id;
    }

    public CompletableFuture<?> getTask(String id) {
        return tasks.get(id);
    }

    @PreDestroy
    public void shutdown() {
        executor.shutdown();
    }
}
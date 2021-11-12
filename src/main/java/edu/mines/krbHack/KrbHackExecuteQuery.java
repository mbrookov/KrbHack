package edu.mines.krbHack;

import edu.mines.krbHack.search.Operand;
import edu.mines.krbHack.search.Search;
import org.identityconnectors.common.logging.Log;
import org.identityconnectors.framework.common.exceptions.ConnectionBrokenException;
import org.identityconnectors.framework.common.exceptions.ConnectorException;
import org.identityconnectors.framework.common.objects.ObjectClass;
import org.identityconnectors.framework.common.objects.OperationOptions;
import org.identityconnectors.framework.common.objects.ResultsHandler;

import java.io.IOException;

public class KrbHackExecuteQuery {
    private static final Log LOG = Log.getLog(KrbHackExecuteQuery.class);

    private KrbHackConnection connection = null;

    private Operand filter = null;

    private ResultsHandler resultsHandler = null;

    private ObjectClass objectClass = null;

    private OperationOptions options = null;

    public KrbHackExecuteQuery(final KrbHackConnection connection, final ObjectClass objectClass, final Operand filter,
                               final OperationOptions options, final ResultsHandler resultsHandler) throws IOException {
        this.connection = connection;
        this.filter = filter;
        this.objectClass = objectClass;
        this.options = options;
        this.resultsHandler = resultsHandler;
    }

    public final void executeQuery() {
        try {
            doExecuteQuery();
        } catch (IOException e) {
            throw new ConnectorException(e.getMessage(), e);
        } catch (InterruptedException e) {
            throw new ConnectionBrokenException(e);
        }
    }

    private void doExecuteQuery() throws IOException, InterruptedException {

        if (!objectClass.equals(ObjectClass.ACCOUNT) && (!objectClass.equals(ObjectClass.GROUP))) {
            throw new IllegalStateException("Wrong object class");
        }

        if (filter == null) {
            new Search(connection, resultsHandler, objectClass, null, options).searchAll();
            return;
        }
        switch (filter.getOperator()) {
            case EQ:
                new Search(connection, resultsHandler, objectClass, filter, options).equalSearch();
                break;
            case SW:
                new Search(connection, resultsHandler, objectClass, filter, options).startsWithSearch();
                break;
            case EW:
                new Search(connection, resultsHandler, objectClass, filter, options).endsWithSearch();
                break;
            case C:
                new Search(connection, resultsHandler, objectClass, filter, options).containsSearch();
                break;
            case OR:
                new Search(connection, resultsHandler, objectClass, filter.getFirstOperand(), options).orSearch();
                break;
            case AND:
                new Search(connection, resultsHandler, objectClass, filter, options).andSearch();
                break;
            default:
                throw new ConnectorException("Wrong Operator");
        }

    }
}

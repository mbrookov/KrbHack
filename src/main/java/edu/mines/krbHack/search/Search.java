package edu.mines.krbHack.search;

import edu.mines.krbHack.KrbHackConnection;
import org.identityconnectors.common.logging.Log;
import org.identityconnectors.framework.common.objects.ObjectClass;
import org.identityconnectors.framework.common.objects.OperationOptions;
import org.identityconnectors.framework.common.objects.ResultsHandler;

import java.io.IOException;

public class Search {
    private static final Log LOG = Log.getLog(Search.class);

    private KrbHackConnection connection = null;

    private Operand filter = null;

    private ResultsHandler resultsHandler = null;

    private ObjectClass objectClass = null;

    private OperationOptions options = null;

    public Search(final KrbHackConnection connection, final ResultsHandler resultsHandler, final ObjectClass objectClass,
                  final Operand filter, final OperationOptions options) {
        this.connection = connection;
        this.resultsHandler = resultsHandler;
        this.objectClass = objectClass;
        this.filter = filter;
        this.options = options;
    }

    public void searchAll() throws IOException, InterruptedException {
        if (objectClass.equals(ObjectClass.ACCOUNT)) {
/*            PasswdFile passwdFile = searchAllUsers();
            fillUserHandler(passwdFile.getPasswdRows(), false); */

        } else if (objectClass.equals(ObjectClass.GROUP)) {
/*            GroupFile groupFile = searchAllGroups();
            fillGroupHandler(groupFile.getGroupRows()); */
        }
    }

    public void equalSearch() throws IOException, InterruptedException {
        if (objectClass.equals(ObjectClass.ACCOUNT)) {

/*            PasswdFile passwdFile = (filter.isUid() ? searchUserByUid() : searchAllUsers());
            fillUserHandler(passwdFile.searchRowByAttribute(filter.getAttributeName(), filter.getAttributeValue(),
                    filter.isNot()), true);*/

        } else if (objectClass.equals(ObjectClass.GROUP)) {
/*            GroupFile groupFile = (filter.isUid() ? searchGroupByUid() : searchAllGroups());
            fillGroupHandler(groupFile.searchRowByAttribute(filter.getAttributeName(), filter.getAttributeValue(),
                    filter.isNot()));*/
        }
    }


    public void startsWithSearch() throws IOException, InterruptedException {
    }


    public void endsWithSearch() {
    }

    public void containsSearch() {
    }

    public void orSearch() {
    }

    public void andSearch() {
    }
}

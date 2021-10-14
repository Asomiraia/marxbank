package it1901.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import it1901.AccountFactory;
import it1901.DataManager;
import it1901.model.User;
import it1901.model.Account;
import it1901.util.AccountType;

public class AccountDeserializer extends StdDeserializer<Account> {

    public AccountDeserializer() {
        this(null);
    }

    public AccountDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Account deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        
        JsonNode node = jp.getCodec().readTree(jp);
        AccountType type = AccountType.valueOf(node.get("type").asText());

        if(!DataManager.manager().checkIfUserExists(node.get("user").get("id").asText())) throw new IllegalStateException("user doesn't exist");
        User owner = DataManager.manager().getUser(node.get("user").get("id").asText());

        Account account = AccountFactory.createFrom(type.getTypeString(), node.get("id").asText(), owner, node.get("name").asText(), node.get("accountNumber").asInt());
        if (account==null) {
            return null;
        }
        account.setBalance(node.get("balance").asDouble());
        return account;
    }
    
}

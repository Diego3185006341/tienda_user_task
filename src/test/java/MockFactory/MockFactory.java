package MockFactory;

import com.store.Model.UserEntity;

import java.time.LocalDate;

public class MockFactory {



    public static UserEntity userEntity(){
        return  UserEntity.
                builder()
                .userIdentification("123")
                .userName("John Doe")
                .userEmail("johndoe@example.com")
                .entryDate(LocalDate.now())
                .password("password")
                .build();
    }
}

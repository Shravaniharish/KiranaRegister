package api.kirana.register.stores.repo;

import api.kirana.register.stores.entity.Stores;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Nested
@ExtendWith(MockitoExtension.class)
public class StoresDAOTest {
    @Mock private StoresRepository storeRepository;

    @InjectMocks private StoresDAO storeDAO;

    @Test
    public void getAllStoreShouldReturnSuccess() {

        int page = 2;
        int size = 4;
        List<Stores> stores = Arrays.asList(new Stores(), new Stores(), new Stores(), new Stores());
        Page<Stores> storeList = new PageImpl<>(stores);
        Pageable pageable = PageRequest.of(page, size);
        Mockito.when(storeRepository.findAll(pageable)).thenReturn(storeList);
        Page<Stores> actualList = storeDAO.getAllStores(pageable);
        Assertions.assertNotNull(actualList);
        Assertions.assertEquals(storeList, actualList);
    }

    @Test
    public void getStoreByIdShouldReturnSuccess() {
        Stores store = new Stores();
        store.setId("test");
        Optional<Stores> expectedStore = Optional.of(store);
        Mockito.when(storeRepository.findById(store.getId())).thenReturn(expectedStore);
        Optional<Stores> actualStore = storeDAO.getStoreById(store.getId());
        Assertions.assertNotNull(actualStore);
        Assertions.assertEquals(expectedStore, actualStore);
    }

    @Test
    public void saveStoreShouldReturnSuccess() {
        Stores store = new Stores();
        store.setId("test");
        store.setName("xyz");
        store.setAddress("Koramangala");
        store.setPhoneNumber("0987654321");
        Mockito.when(storeRepository.save(store)).thenReturn(store);
        Stores actualStore = storeDAO.saveStore(store);
        Assertions.assertNotNull(actualStore);
        Assertions.assertEquals(store, actualStore);
    }

    @Test
    public void deleteStoreShouldReturnSuccess() {
        String storeId = "test";
        Mockito.doNothing().when(storeRepository).deleteById(storeId);
        String response = storeDAO.deleteStore(storeId);
        Mockito.verify(storeRepository, Mockito.times(1)).deleteById(storeId);
    }
}

package api.kirana.register.stores.service;

import api.kirana.register.stores.entity.Stores;
import api.kirana.register.stores.models.StoresDTO;
import api.kirana.register.stores.repo.StoresDAO;
import org.junit.jupiter.api.Assertions;
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

@ExtendWith(MockitoExtension.class)
public class StoresServiceTest {
    @Mock private StoresDAO storeDAO;

    @InjectMocks private StoresServiceImpl storeService;

    @Test
    public void getAllStoreShouldReturnSuccess() {
        int page = 2;
        int size = 4;
        List<Stores> stores = Arrays.asList(new Stores(), new Stores(), new Stores(), new Stores());
        Page<Stores> storeList = new PageImpl<>(stores);
        Pageable pageable = PageRequest.of(page, size);
        Mockito.when(storeDAO.getAllStores(pageable)).thenReturn(storeList);
        Page<Stores> actualList = storeService.getAllStore(pageable);
        Assertions.assertNotNull(actualList);
        Assertions.assertEquals(storeList, actualList);
    }

    @Test
    public void getStoreByIdShouldReturnSuccess() {
        Stores store = new Stores();
        store.setId("test");
        Optional<Stores> expectedStore = Optional.of(store);
        Mockito.when(storeDAO.getStoreById(store.getId())).thenReturn(expectedStore);
        Optional<Stores> actualStore = storeService.getStoreById(store.getId());
        Assertions.assertNotNull(actualStore);
        Assertions.assertEquals(expectedStore, actualStore);
    }

    @Test
    public void saveStoreShouldReturnSuccess() {
        Stores store = new Stores();
        store.setName("abc");
        store.setPhoneNumber("0987654321");
        store.setAddress("xyz");

        StoresDTO storeDTO = new StoresDTO();
        storeDTO.setName("abc");
        storeDTO.setPhoneNumber("0987654321");
        storeDTO.setAddress("xyz");
        Mockito.when(storeDAO.saveStore(Mockito.any(Stores.class))).thenReturn(store);
        Stores actualStore = storeService.saveStore(storeDTO);
        Assertions.assertNotNull(actualStore);
        Assertions.assertEquals(store.getAddress(), actualStore.getAddress());
        Assertions.assertEquals(store.getName(), actualStore.getName());
        Assertions.assertEquals(store.getPhoneNumber(), actualStore.getPhoneNumber());
    }

    @Test
    public void deleteStoreShouldReturnSuccess() {
        String storeId = "test";
        String expectedResponse = "test";
        Mockito.doNothing().when(storeDAO).deleteStore(storeId);
        String actualResponse = storeService.deleteStore(storeId);
        Mockito.verify(storeDAO, Mockito.times(1)).deleteStore(storeId);
        Assertions.assertEquals(expectedResponse, actualResponse);
    }
}

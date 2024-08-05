package api.kirana.register.stores.controllers;
import api.kirana.register.response.ApiResponse;
import api.kirana.register.stores.models.StoresDTO;
import api.kirana.register.stores.service.StoresService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/stores")
public class StoresController {
    private final StoresService storeService;

    @Autowired
    public StoresController(StoresService storeService) {
        this.storeService = storeService;
    }

    /**
     * Retrieves a list of all stores.
     *
     * @return a ResponseEntity containing the response with all stores
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse>  getAllStores(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "2") int size) {
        ApiResponse response = new ApiResponse();
        Pageable pageable = PageRequest.of(page, size);
        response.setData(storeService.getAllStore(pageable));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Retrieves a store by its ID.
     *
     * @param id the ID of the store to retrieve
     * @return a ResponseEntity containing the response with the store information
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping()
    public ResponseEntity<ApiResponse> getStoreById(@Valid @RequestParam String id) {
        ApiResponse response = new ApiResponse();
        response.setData(storeService.getStoreById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Saves a new store.
     *
     * @param storeRequest the request body containing store information to save
     * @return a ResponseEntity containing the response with the saved store information
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public ResponseEntity<ApiResponse> saveStore(@Valid @RequestBody StoresDTO storeRequest) {
        ApiResponse response = new ApiResponse();
        response.setData(storeService.saveStore(storeRequest));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Deletes a store by its ID.
     *
     * @param id the ID of the store to delete
     * @return a ResponseEntity containing the response indicating the deletion status
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping()
    public ResponseEntity<ApiResponse> deleteStore(@Valid @RequestParam String id) {
        ApiResponse response = new ApiResponse();
        response.setData(storeService.deleteStore(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

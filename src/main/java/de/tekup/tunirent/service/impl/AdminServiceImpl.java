package de.tekup.tunirent.service.impl;

import de.tekup.tunirent.exception.ResourceNotFoundException;
import de.tekup.tunirent.model.Admin;
import de.tekup.tunirent.repository.AdminRepository;
import de.tekup.tunirent.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Admin", "Id", id));
    }

    @Override
    public Admin updateAdmin(Admin admin, Long id) {
        Admin existingAdmin = adminRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin", "Id", id));
        existingAdmin.setFirstName(admin.getFirstName());
        existingAdmin.setLastName(admin.getLastName());
        existingAdmin.setUsername(admin.getUsername());
        existingAdmin.setPassword(admin.getPassword());
        adminRepository.save(existingAdmin);
        return existingAdmin;
    }

    @Override
    public void deleteAdminById(Long id) {
        getAdminById(id);
        adminRepository.deleteById(id);
    }
}

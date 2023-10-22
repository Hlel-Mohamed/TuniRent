package de.tekup.tunirent.service;

import de.tekup.tunirent.model.Admin;

import java.util.List;
public interface AdminService {
    Admin saveAdmin(Admin admin);
    List<Admin> getAllAdmins();
    Admin getAdminById(Long id);
    Admin updateAdmin(Admin admin, Long id);
    void deleteAdminById(Long id);
}

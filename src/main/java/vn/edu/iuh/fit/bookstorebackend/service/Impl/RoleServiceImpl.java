package vn.edu.iuh.fit.bookstorebackend.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.bookstorebackend.dto.request.CreateRoleRequest;
import vn.edu.iuh.fit.bookstorebackend.dto.response.RoleResponse;
import vn.edu.iuh.fit.bookstorebackend.model.Role;
import vn.edu.iuh.fit.bookstorebackend.repository.RoleRepository;
import vn.edu.iuh.fit.bookstorebackend.service.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleResponse createRole(CreateRoleRequest request) {
        Role role = new Role();
        role.setCode(request.getCode());
        role.setName(request.getName());
        Role saved = roleRepository.save(role);
        return toResponse(saved);
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        return roleRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public RoleResponse getRoleById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found: " + id));
        return toResponse(role);
    }

    @Override
    public RoleResponse updateRole(Long id, CreateRoleRequest request) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found: " + id));
        if (request.getCode() != null) role.setCode(request.getCode());
        if (request.getName() != null) role.setName(request.getName());
        Role updated = roleRepository.save(role);
        return toResponse(updated);
    }

    @Override
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role not found: " + id);
        }
        roleRepository.deleteById(id);
    }

    private RoleResponse toResponse(Role role) {
        RoleResponse r = new RoleResponse();
        r.setId(role.getId());
        r.setCode(role.getCode());
        r.setName(role.getName());
        return r;
    }
}



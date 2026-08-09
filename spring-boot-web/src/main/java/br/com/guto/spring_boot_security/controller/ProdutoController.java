package br.com.guto.spring_boot_security.controller;

import br.com.guto.spring_boot_security.database.model.ProdutoModel;
import br.com.guto.spring_boot_security.dto.ProdutoDto;
import br.com.guto.spring_boot_security.exception.NotFoundException;
import br.com.guto.spring_boot_security.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoModel> findAll() {
        return produtoService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel createProduct(@RequestBody ProdutoDto produtoDto) {
        return produtoService.createProduct(produtoDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ProdutoModel updateProduct(@RequestBody ProdutoDto produtoDto,
                                      @PathVariable Integer id) throws NotFoundException {
        return produtoService.updateProduct(produtoDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Integer id) {
        produtoService.deleteProduct(id);
    }

}

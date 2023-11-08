package com.dubrouskaya.cloud.controller;


import com.dubrouskaya.jmpdto.dto.request.SubscriptionRequestDto;
import com.dubrouskaya.jmpdto.dto.response.SubscriptionResponseDto;
import com.dubrouskaya.jmpserviceapi.SubscriptionService;
import com.dubrouskaya.jmpservicerest.controller.SubscriptionController;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/subscription")
public class SubscriptionControllerImpl implements SubscriptionController {

    private static final String SUBSCRIPTIONS_REL = "subscriptions";
    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionControllerImpl(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    @Override
    public SubscriptionResponseDto createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        SubscriptionResponseDto subscription = subscriptionService.createSubscription(subscriptionRequestDto);
        subscription.add(
                linkTo(methodOn(SubscriptionControllerImpl.class).createSubscription(subscriptionRequestDto)).withSelfRel(),
                linkTo(methodOn(SubscriptionControllerImpl.class).updateSubscription(subscriptionRequestDto)).withRel("update"),
                linkTo(methodOn(SubscriptionControllerImpl.class).getSubscription(subscription.getId())).withRel("subscription"),
                linkTo(methodOn(SubscriptionControllerImpl.class).getAllSubscriptions()).withRel(SUBSCRIPTIONS_REL)
        );
        return subscription;
    }

    @PutMapping
    @Override
    public SubscriptionResponseDto updateSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        SubscriptionResponseDto subscription = subscriptionService.updateSubscription(subscriptionRequestDto);
        subscription.add(
                linkTo(methodOn(SubscriptionControllerImpl.class).updateSubscription(subscriptionRequestDto)).withSelfRel(),
                linkTo(methodOn(SubscriptionControllerImpl.class).createSubscription(subscriptionRequestDto)).withRel("create"),
                linkTo(methodOn(SubscriptionControllerImpl.class).getSubscription(subscription.getId())).withRel("subscription"),
                linkTo(methodOn(SubscriptionControllerImpl.class).getAllSubscriptions()).withRel(SUBSCRIPTIONS_REL)
        );
        return subscription;
    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteSubscription(@Parameter(name = "id") @PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
    }

    @GetMapping("/{id}")
    @Override
    public SubscriptionResponseDto getSubscription(@Parameter(name = "id") @PathVariable Long id) {
        SubscriptionResponseDto subscription = subscriptionService.getSubscription(id);
        subscription.add(
                linkTo(methodOn(SubscriptionControllerImpl.class).getAllSubscriptions()).withRel(SUBSCRIPTIONS_REL),
                linkTo(methodOn(SubscriptionControllerImpl.class).getSubscription(id)).withSelfRel());
        return subscription;
    }

    @GetMapping
    @Override
    public CollectionModel<SubscriptionResponseDto> getAllSubscriptions() {
        List<SubscriptionResponseDto> subscriptions = subscriptionService.getAllSubscriptions();
        subscriptions.forEach(subscription -> subscription.add(
                linkTo(methodOn(SubscriptionControllerImpl.class).getSubscription(subscription.getId())).withSelfRel()));
        Link allSubscriptionsLink = linkTo(methodOn(SubscriptionControllerImpl.class).getAllSubscriptions()).withSelfRel();
        return CollectionModel.of(subscriptions, allSubscriptionsLink);
    }
}

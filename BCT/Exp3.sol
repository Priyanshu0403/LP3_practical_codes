// SPDX-License-Identifier: MIT
pragma solidity >=0.6.2 <0.9.0;

contract SimpleBank {
    mapping (address => uint) private balances;

    function deposit(uint amount) public returns (uint) {
        balances[msg.sender] += amount;
        return balances[msg.sender];
    }

    function withdraw(uint withdrawAmount) public returns (uint remainingBal) {
        // Check enough balance available, otherwise just return balance
        if (withdrawAmount <= balances[msg.sender]) {
            balances[msg.sender] -= withdrawAmount;
        }
        return balances[msg.sender];
    }

    function balance() public view returns (uint) {
        return balances[msg.sender];
    }

}



